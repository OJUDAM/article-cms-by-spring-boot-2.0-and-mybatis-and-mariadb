package com.example.starter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.starter.dto.Article;
import com.example.starter.dto.ArticleReply;

@Mapper //이렇게 해주면 ArticleDao의 구현체를 마이바티스가 대신 구현해준다.
public interface ArticleDao {
	public List<Article> getList(Map<String, Object> args);

	public void add(Map<String, Object> param);
	
	public int getTotalCount();

	public Article getOne(long id);
	
	public void delete(long id);

	public void modify(Map<String, Object> param);
	
	public void hitUp(long id);
	
	public void update(Map<String, Object> args);
	
	public List<ArticleReply> getReplyList(Map<String, Object> args);
	
	public long deleteReply(int args);
	
	public long doAddReply(Map<String, Object> args);
	
	public void deleteAll(int args);
	
	public void oldSort(Map<String, Object> args);
	
	public List<Article> sort();
	
	public int getCount(Map<String, Object> args);

	public void change(Map<String, Object> param);
	
}
