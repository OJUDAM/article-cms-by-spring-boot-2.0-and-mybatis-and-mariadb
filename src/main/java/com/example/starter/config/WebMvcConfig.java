package com.example.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	//beforeActionInterceptor 인텁세터 불러오기
	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforActionInterceptor;
	
	//인터셉터 적용하는 역활
	public void addInterceptors(InterceptorRegistry registry) {
		//beforeActionInterceptor 을 모든 액션(/**)에 연결, 단 /resource로 시작하는 액션은 제외
		registry.addInterceptor(beforActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");
	
	}
}
