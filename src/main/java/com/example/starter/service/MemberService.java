package com.example.starter.service;

import java.util.Map;

import com.example.starter.dto.Member;

public interface MemberService {
	public Map<String, Object> checkLoginIdDup(String loginId);

	public Map<String, Object> join(Map<String, Object> param);

	public Member getMatchedOne(String loginId, String loginPw);

	public Member getOne(long loginedMemberId);
	
	public void doChange(Map<String, Object> args);
	
	public void secession(long secession);
}
