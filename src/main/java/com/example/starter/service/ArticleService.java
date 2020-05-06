package com.example.starter.service;

import java.util.List;
import java.util.Map;

import com.example.starter.dto.Article;
import com.example.starter.dto.ArticleReply;

public interface ArticleService {
	public List<Article> getList(Map<String, Object> args);

	public long add(Map<String, Object> param);

	public int getTotalCount();

	public Article getOne(long id);

	public void delete(long id);

	public void modify(Map<String, Object> param);
	
	public void change(Map<String, Object> param);

	public void hitUp(long id);
	
	public void update(Map<String, Object> args);
	
	public List<ArticleReply> getReplyList(Map<String, Object> args);
	
	public long deleteReply(int article);
	
	public long doAddReply(Map<String, Object> args);
	
	public void oldSort(Map<String, Object> args);
	
	public List<Article> sort();
	
	public Map<String, Object> getPagedList(Map<String, Object> param);
	
	public int getCount(Map<String, Object> args);
	
	
}
