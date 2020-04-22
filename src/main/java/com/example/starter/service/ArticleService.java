package com.example.starter.service;

import java.util.List;
import java.util.Map;

import com.example.starter.dto.Article;

public interface ArticleService {
	public List<Article> getList();

	public void add(Map<String, Object> param);
	
}
