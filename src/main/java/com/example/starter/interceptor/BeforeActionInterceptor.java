package com.example.starter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.starter.dto.Member;
import com.example.starter.service.MemberService;


@Component("beforeActionInterceptor") //컴포넌트 이름 설정
public class BeforeActionInterceptor implements HandlerInterceptor{
	@Autowired
	MemberService memberService;

	/*
	 * @Value("${custom.logoText}") String logoText;
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isLogined = false;
		long loginedMemberId = 0;
		Member loginedMember = null;
		
		HttpSession session = request.getSession();
		
		/*
		 * request.setAttribute("logoText", logoText); request.setAttribute("logoHtml",
		 * logoText);
		 */		if(session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (long)session.getAttribute("loginedMemberId");
			loginedMember = memberService.getOne(loginedMemberId);
		}
		
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("loginedMember", loginedMember);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	

}
