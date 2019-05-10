package com.rtmart.readwalk;

import com.rtmart.readwalk.article.dao.ArticleDao;
import com.rtmart.readwalk.article.dao.UserDao;
import com.rtmart.readwalk.article.dto.UserDto;
import com.rtmart.readwalk.article.entity.Article;
import com.rtmart.readwalk.article.entity.User;
import com.rtmart.readwalk.core.response.ResponseDto;
import com.rtmart.readwalk.core.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
@ServletComponentScan(basePackages = { "com.rtmart.readwalk.core" })
public class ReadWalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadWalkApplication.class, args);
    }

}
