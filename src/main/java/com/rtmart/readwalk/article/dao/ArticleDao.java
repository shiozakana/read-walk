package com.rtmart.readwalk.article.dao;

import com.rtmart.readwalk.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ArticleDao extends JpaRepository<Article,Long> {

}
