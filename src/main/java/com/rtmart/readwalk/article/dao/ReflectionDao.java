package com.rtmart.readwalk.article.dao;

import com.rtmart.readwalk.article.entity.Reflection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReflectionDao extends JpaRepository<Reflection,Long> {

    List<Reflection> findByArticleIdOrderByCreateTimeDesc(Long articleId);
}
