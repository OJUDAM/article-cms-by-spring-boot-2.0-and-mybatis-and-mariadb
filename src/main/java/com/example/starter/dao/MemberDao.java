package com.example.starter.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper //이렇게 해주면 ArticleDao의 구현체를 마이바티스가 대신 구현해준다.
public interface MemberDao {
	public int getLoginIdDupCount(String loginId);
	
	public void join(Map<String, Object> param);
}