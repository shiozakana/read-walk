package com.rtmart.readwalk.article.dao;

import com.rtmart.readwalk.article.entity.ILike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeDao extends JpaRepository<ILike,Long> {

    List<ILike> findByContentIdAndLikeNoAndStoreAndType(Long id,String likeNo,String store,String type);

    int countByContentIdAndType(Long contentId,String type);

    int deleteByContentIdAndLikeNoAndStoreAndType(Long id,String likeNo,String store,String type);
}
