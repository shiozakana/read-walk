package com.rtmart.readwalk.article.controller;

import com.rtmart.readwalk.article.current.AuthInfo;
import com.rtmart.readwalk.article.dao.ArticleDao;
import com.rtmart.readwalk.article.dao.ILikeDao;
import com.rtmart.readwalk.article.dao.ReflectionDao;
import com.rtmart.readwalk.article.dto.*;
import com.rtmart.readwalk.article.entity.Article;
import com.rtmart.readwalk.article.entity.ILike;
import com.rtmart.readwalk.article.entity.Reflection;
import com.rtmart.readwalk.core.response.ResponseDto;
import com.rtmart.readwalk.core.response.ResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/article")
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ReflectionDao reflectionDao;

    @Autowired
    private ILikeDao iLikeDao;

    /**
     * 文章点赞
     * @param articleId
     * @return
     */
    @PostMapping("/like/{articleId}")
    public ResponseDto<Void> likeArticle(@PathVariable Long articleId) {
        UserDto authInfo = AuthInfo.getAuthInfo();

        List<ILike> exist = iLikeDao.findByContentIdAndLikeNoAndStoreAndType(articleId, authInfo.getUserNo(), authInfo.getStore(), "1");
        if(exist.isEmpty()){
            ILike iLike = new ILike();
            iLike.setContentId(articleId);
            iLike.setLikeName(authInfo.getName());
            iLike.setLikeNo(authInfo.getUserNo());
            iLike.setArea(authInfo.getArea());
            iLike.setStore(authInfo.getStore());
            iLike.setType("1");
            iLike.setLikeTime(new Date());

            iLikeDao.save(iLike);
        }else{
            iLikeDao.deleteById(exist.get(0).getId());
        }

        return ResponseUtil.success();
    }

    /**
     * 列表查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    public ResponseDto<ArticleListDto> getArticles(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        ArticleListDto result = new ArticleListDto();
        UserDto authInfo = AuthInfo.getAuthInfo();
        Pageable pageable = PageRequest.of(pageNum-1, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        Page<Article> page = articleDao.findAll(pageable);
        long total = page.getTotalElements();
        List<Article> articles = page.getContent();

        List<ArticleDto> articleDtos = new ArrayList<>();
        for (Article article : articles) {
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article, articleDto);
            int likeCount = iLikeDao.countByContentIdAndType(article.getId(), "1");
            articleDto.setLikeCount(likeCount);
            List<ILike> articleLikes = iLikeDao.findByContentIdAndLikeNoAndStoreAndType(article.getId(), authInfo.getUserNo(), authInfo.getStore(), "1");
            if (articleLikes.isEmpty()) {
                articleDto.setLikeFlag(false);
            } else {
                articleDto.setLikeFlag(true);
            }
            articleDtos.add(articleDto);
        }

        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(total);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        result.setPageInfo(pageInfo);
        result.setDataList(articleDtos);

        return ResponseUtil.success(result);
    }

    /**
     * 详情查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseDto<ArticleDto> articleDetail(@PathVariable("id") Long id) {
        ArticleDto result = new ArticleDto();

        Article article = articleDao.findById(id).get();
        BeanUtils.copyProperties(article, result);

        UserDto authInfo = AuthInfo.getAuthInfo();
        List<Reflection> reflections = reflectionDao.findByArticleIdOrderByCreateTimeDesc(id);
        //查找文章的赞
        List<ILike> articleLikes = iLikeDao.findByContentIdAndLikeNoAndStoreAndType(id, authInfo.getUserNo(), authInfo.getStore(), "1");
        if (articleLikes.isEmpty()) {
            result.setLikeFlag(false);
        } else {
            result.setLikeFlag(true);
        }
        int likeCount = iLikeDao.countByContentIdAndType(article.getId(), "1");
        result.setLikeCount(likeCount);

        //查找读后感的赞
        List<ReflectionDto> reflectionDtos = new ArrayList<>();
        if (reflections != null && !reflections.isEmpty()) {
            for (Reflection reflection : reflections) {
                ReflectionDto reflectionDto = new ReflectionDto();
                List<ILike> reflectionLikes = iLikeDao.findByContentIdAndLikeNoAndStoreAndType(reflection.getId(), authInfo.getUserNo(), authInfo.getStore(), "2");
                BeanUtils.copyProperties(reflection, reflectionDto);
                if (reflectionLikes.isEmpty()) {
                    reflectionDto.setLikeFlag(false);
                } else {
                    reflectionDto.setLikeFlag(true);
                }
                int refLikeCount = iLikeDao.countByContentIdAndType(reflection.getId(), "2");
                reflectionDto.setLikeCount(refLikeCount);
                reflectionDtos.add(reflectionDto);
            }
        }
        result.setReflectionDtos(reflectionDtos);

        return ResponseUtil.success(result);
    }

}
