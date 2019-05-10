package com.rtmart.readwalk.article.controller;

import com.rtmart.readwalk.article.current.AuthInfo;
import com.rtmart.readwalk.article.dao.ILikeDao;
import com.rtmart.readwalk.article.dao.ReflectionDao;
import com.rtmart.readwalk.article.dto.ReflectionDto;
import com.rtmart.readwalk.article.dto.UserDto;
import com.rtmart.readwalk.article.entity.ILike;
import com.rtmart.readwalk.article.entity.Reflection;
import com.rtmart.readwalk.core.response.ResponseDto;
import com.rtmart.readwalk.core.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/reflection")
public class ReflectionController {

    @Autowired
    private ReflectionDao reflectionDao;

    @Autowired
    private ILikeDao iLikeDao;

    @PostMapping
    public ResponseDto addReflection(@RequestBody ReflectionDto reflectionDto){
        UserDto authInfo = AuthInfo.getAuthInfo();

        Reflection reflection = new Reflection();
        reflection.setArticleId(reflectionDto.getArticleId());
        reflection.setContent(reflectionDto.getContent());
        reflection.setCreateTime(new Date());
        reflection.setChangeTime(new Date());
        reflection.setAuthorName(authInfo.getName());
        reflection.setAuthorNo(authInfo.getUserNo());
        reflection.setStore(authInfo.getStore());
        reflectionDao.save(reflection);

        return ResponseUtil.success();
    }

    @PostMapping("/like/{reflectionId}")
    public ResponseDto<Void> likeArticle(@PathVariable Long reflectionId) {
        UserDto authInfo = AuthInfo.getAuthInfo();

        List<ILike> exist = iLikeDao.findByContentIdAndLikeNoAndStoreAndType(reflectionId, authInfo.getUserNo(), authInfo.getStore(), "2");
        if(exist.isEmpty()){
            ILike iLike = new ILike();
            iLike.setContentId(reflectionId);
            iLike.setLikeName(authInfo.getName());
            iLike.setLikeNo(authInfo.getUserNo());
            iLike.setArea(authInfo.getArea());
            iLike.setStore(authInfo.getStore());
            iLike.setType("2");
            iLike.setLikeTime(new Date());

            iLikeDao.save(iLike);
        }else{
            iLikeDao.deleteById(exist.get(0).getId());
        }

        return ResponseUtil.success();
    }
}
