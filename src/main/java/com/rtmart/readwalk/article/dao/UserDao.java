package com.rtmart.readwalk.article.dao;

import com.rtmart.readwalk.article.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    List<User> findByUserNoAndStoreAndPrivilege(String userNo,String store,String privilege);

    List<User> findByUserNoAndPrivilege(String userNo,String privilege);
}
