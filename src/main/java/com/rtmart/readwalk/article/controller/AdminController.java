package com.rtmart.readwalk.article.controller;

import com.rtmart.readwalk.article.dao.ArticleDao;
import com.rtmart.readwalk.article.dao.ReflectionDao;
import com.rtmart.readwalk.article.dao.UserDao;
import com.rtmart.readwalk.article.dto.AdminUserDto;
import com.rtmart.readwalk.article.dto.ArticleDto;
import com.rtmart.readwalk.article.dto.ReflectionDto;
import com.rtmart.readwalk.article.dto.SelectDto;
import com.rtmart.readwalk.article.entity.Article;
import com.rtmart.readwalk.article.entity.Reflection;
import com.rtmart.readwalk.article.entity.User;
import com.rtmart.readwalk.core.util.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ReflectionDao reflectionDao;

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest req) {
        String userNo = req.getParameter("userNo");
        String password = req.getParameter("password");
        List<User> users = userDao.findByUserNoAndPrivilege(userNo, "2");
        if (users.isEmpty()) {
            return "login";
        }
        User user = users.get(0);
        if (!PasswordUtil.checkpassword(password, user.getPassword())) {
            model.addAttribute("userNo", userNo);
            return "login";
        }
        HttpSession session = req.getSession();
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setName(user.getName());
        adminUserDto.setUserNo(user.getUserNo());
        session.setAttribute("user", adminUserDto);
        return "redirect:/admin/index";
    }


    @GetMapping("/login")
    public String toLogin(Model model, HttpServletRequest req) {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/article")
    public String article() {
        return "article";
    }

    @GetMapping("/reflection")
    public String reflection() {
        return "reflection";
    }

    @PostMapping("/article")
    public String article(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        article.setCreateTime(new Date());
        article.setChangeTime(new Date());

        articleDao.save(article);
        return "article";
    }

    @PostMapping("/reflection")
    public String reflection(ReflectionDto reflectionDto) {
        Reflection reflection = new Reflection();
        BeanUtils.copyProperties(reflectionDto, reflection);
        reflection.setChangeTime(reflectionDto.getCreateTime());

        reflectionDao.save(reflection);
        return "reflection";
    }

    @PostMapping("/store")
    @ResponseBody
    public List<SelectDto> load() {
        SelectDto selectDto1 = new SelectDto();
        selectDto1.setText("苏州仓");
        selectDto1.setId(1);
        selectDto1.setValue("苏州仓");

        SelectDto selectDto2 = new SelectDto();
        selectDto2.setText("南京仓");
        selectDto1.setId(2);
        selectDto2.setValue("南京仓");

        List<SelectDto> selectDtos = new ArrayList<>();
        selectDtos.add(selectDto1);
        selectDtos.add(selectDto2);

        return selectDtos;
    }
}
