package com.example.starter.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.starter.dto.Member;
import com.example.starter.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/member/login")
		public String showLogin() {
			return "member/login";
	}
	
	@RequestMapping("/member/doLogout")
	public String doLogout(HttpSession session) {
		session.removeAttribute("loginedMemberId");
		
		return "redirect:/";
	}
	
	@RequestMapping("/member/doLogin")
	public String doLogin(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		Member matchedMember = memberService.getMatchedOne((String) param.get("loginId"),
							(String) param.get("loginPw"));
		
		if(matchedMember == null) {
			model.addAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
			model.addAttribute("historyBack",true);
			return "common/redirect";
		}
		
		session.setAttribute("loginedMemberId",matchedMember.getId());
		
		model.addAttribute("alertMsg", "로그인 되었습니다.");
		model.addAttribute("redirectUrl","/");
		
		return "common/redirect";
	}

	@RequestMapping("/member/join")
	public String showJoin() {
		return "member/join";
	}
	
	@RequestMapping("/member/doJoin")
	public String doJoin(@RequestParam Map<String, Object> param, Model model) {
		//로그인 ID의 중복성 체크
		Map<String, Object> checkLoginIdDupRs = memberService.checkLoginIdDup((String)param.get("loginId"));
		
		if( ((String)checkLoginIdDupRs.get("resultCode")).startsWith("F-")) {
			model.addAttribute("alertMsg", checkLoginIdDupRs.get("msg"));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		Map<String, Object> joinRs = memberService.join(param);
		
		if( ((String)joinRs.get("resultCode")).startsWith("F-")) {
			model.addAttribute("alertMsg", joinRs.get("msg"));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		model.addAttribute("alertMsg", joinRs.get("msg"));
		model.addAttribute("redirectUrl", "/member/login");
		return "common/redirect";
	}
	
	@RequestMapping("member/myPage")
	public String myPage(Model model, HttpSession session) {
		return "member/myPage";
	}
	
	@RequestMapping("member/change")
	public String modify(HttpSession session) {
		return "member/change";
	}
	
	@RequestMapping("member/doChange")
	@ResponseBody
	public String doChange(@RequestParam Map<String, Object> param) {
		StringBuilder sb = new StringBuilder();
		
		memberService.doChange(param);
		sb.append("<script>alert('회원정보수정이 완료되었습니다.');location.replace('/member/myPage');</script>");
		
		return sb.toString();
	}
	
	@RequestMapping("member/secession")
	public String secession(HttpSession session) {
		long secession = (long)session.getAttribute("loginedMemberId");
		memberService.secession(secession);
		
		session.removeAttribute("loginedMemberId");
		
		return "member/main";
	}
}
