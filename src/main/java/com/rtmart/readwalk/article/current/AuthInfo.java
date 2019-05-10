package com.rtmart.readwalk.article.current;

import com.rtmart.readwalk.article.dto.UserDto;

public class AuthInfo {

    static{
        threadLocal = new ThreadLocal<>();
    }

    private static ThreadLocal<UserDto> threadLocal;

    public static void setAuthInfo(UserDto authInfo){
        threadLocal.set(authInfo);
    }

    public static UserDto getAuthInfo(){
        return threadLocal.get();
    }
}
