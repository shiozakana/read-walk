package com.rtmart.readwalk.core.config;

import com.rtmart.readwalk.core.interceptor.BaseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 */
@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {

	@Autowired
	private BaseInterceptor addInterceptors;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(addInterceptors).addPathPatterns("/admin/**");
	}

}
