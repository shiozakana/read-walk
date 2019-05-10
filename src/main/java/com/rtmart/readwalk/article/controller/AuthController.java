package com.rtmart.readwalk.article.controller;

import com.rtmart.readwalk.article.current.AuthInfo;
import com.rtmart.readwalk.article.dao.UserDao;
import com.rtmart.readwalk.article.dto.UserDto;
import com.rtmart.readwalk.article.entity.User;
import com.rtmart.readwalk.core.exception.BusinessException;
import com.rtmart.readwalk.core.response.ResponseDto;
import com.rtmart.readwalk.core.response.ResponseUtil;
import com.rtmart.readwalk.core.util.JwtUtils;
import com.rtmart.readwalk.core.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/sessions")
public class AuthController {

    @Autowired
    private UserDao userDao;

    private static final String DIGIT_REGEX = "^\\d+$";

    private static final Long JWTTTL_SECONDS = 7200L;

    @PostMapping("/sign")
    public ResponseDto<UserDto> sign(@RequestBody UserDto reqUserDto) {
        String store = reqUserDto.getStore();
        String userNo = reqUserDto.getUserNo();
        String name = reqUserDto.getName();
        String password = reqUserDto.getPassword();

        if (StringUtils.isEmpty(store)) {
            throw new BusinessException("请选择仓库");
        }
        if (StringUtils.isEmpty(userNo)) {
            throw new BusinessException("请输入工号");
        }
        if (userNo.length() > 10 || !userNo.matches(DIGIT_REGEX)) {
            throw new BusinessException("请输入正确工号");
        }
        if (StringUtils.isEmpty(name)) {
            throw new BusinessException("请输入姓名");
        }
        if (name.length() > 20) {
            throw new BusinessException("姓名过长");
        }
        if (StringUtils.isBlank(password)) {
            throw new BusinessException("请输入密码");
        }
        if (password.length() < 6 || password.length() > 20) {
            throw new BusinessException("密码为6-20位");
        }
        List<User> users = userDao.findByUserNoAndStoreAndPrivilege(userNo, store, "1");
        if (!users.isEmpty()) {
            throw new BusinessException("用户已存在");
        }
        User user = new User();
        user.setUserNo(userNo);
        user.setName(name);
        user.setPassword(PasswordUtil.EncoderByMd5(password));
        user.setPrivilege("1");
        user.setStore(store);
        userDao.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userNo", userNo);
        claims.put("name", name);
        claims.put("privilege", "1");
        claims.put("store", store);
        long expireMillis = System.currentTimeMillis() + JWTTTL_SECONDS * 1000;
        claims.put("iat", new Date(expireMillis));
        String token = JwtUtils.generateJws(claims);

        UserDto userDto = new UserDto();
        userDto.setUserNo(userNo);
        userDto.setName(name);
        userDto.setPrivilege("1");
        userDto.setStore(store);
        userDto.setToken(token);
        return ResponseUtil.success(userDto);
    }

    @PostMapping("/login")
    public ResponseDto<UserDto> login(@RequestBody UserDto reqUserDto) {
        String store = reqUserDto.getStore();
        String userNo = reqUserDto.getUserNo();
        String password = reqUserDto.getPassword();
        List<User> users = userDao.findByUserNoAndStoreAndPrivilege(userNo, store, "1");
        if (users.isEmpty()) {
            throw new BusinessException("用户不存在");
        }
        User user = users.get(0);
        if (!PasswordUtil.checkpassword(password, user.getPassword())) {
            throw new BusinessException("密码不正确");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userNo", userNo);
        claims.put("name", user.getName());
        claims.put("privilege", "1");
        claims.put("store", store);
        long expireMillis = System.currentTimeMillis() + JWTTTL_SECONDS * 1000;
        claims.put("iat", new Date(expireMillis));
        String token = JwtUtils.generateJws(claims);

        UserDto userDto = new UserDto();
        userDto.setUserNo(userNo);
        userDto.setName(user.getName());
        userDto.setPrivilege("1");
        userDto.setStore(store);
        userDto.setToken(token);

        return ResponseUtil.success(userDto);
    }

    @PostMapping("/login/admin")
    public ResponseDto<UserDto> admimLogin(@RequestBody UserDto reqUserDto) {
        String userNo = reqUserDto.getUserNo();
        String password = reqUserDto.getPassword();
        List<User> users = userDao.findByUserNoAndPrivilege(userNo, "2");
        if (users.isEmpty()) {
            throw new BusinessException("用户不存在");
        }
        User user = users.get(0);
        if (!PasswordUtil.checkpassword(password, user.getPassword())) {
            throw new BusinessException("密码不正确");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userNo", userNo);
        claims.put("name", user.getName());
        claims.put("privilege", "2");
        claims.put("store", user.getStore());
        long expireMillis = System.currentTimeMillis() + JWTTTL_SECONDS * 1000;
        claims.put("iat", new Date(expireMillis));
        String token = JwtUtils.generateJws(claims);

        UserDto userDto = new UserDto();
        userDto.setUserNo(userNo);
        userDto.setName(user.getName());
        userDto.setPrivilege("2");
        userDto.setStore(user.getStore());
        userDto.setToken(token);

        return ResponseUtil.success(userDto);
    }

    @GetMapping("/current")
    public ResponseDto<UserDto> currrent(){
        UserDto authInfo = AuthInfo.getAuthInfo();

        UserDto userDto = new UserDto();
        userDto.setUserNo(authInfo.getUserNo());
        userDto.setName(authInfo.getName());
        userDto.setPrivilege(authInfo.getPrivilege());
        userDto.setStore(authInfo.getStore());
        userDto.setToken(authInfo.getToken());

        return ResponseUtil.success(userDto);
    }


}
