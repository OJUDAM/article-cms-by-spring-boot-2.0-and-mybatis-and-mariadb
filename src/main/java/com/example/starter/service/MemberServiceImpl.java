package com.example.starter.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starter.dao.ArticleDao;
import com.example.starter.dao.MemberDao;
import com.example.starter.dto.Article;
import com.example.starter.util.CUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;
	
	@Override
	public Map<String, Object> checkLoginIdDup(String loginId) {
		int count = memberDao.getLoginIdDupCount(loginId);
		
		String resultCode = "";
		String msg = "";
		
		if(count ==0) {
			resultCode="S-1";
			msg = "사용가능한 로그인 ID 입니다.";
		}
		else {
			resultCode = "F-1";
			msg = "이미 사용중인 로그인 ID 입니다.";
		}
		
		Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("resultCode", resultCode);
		rs.put("msg",msg);
		
		return rs;
	}
}
