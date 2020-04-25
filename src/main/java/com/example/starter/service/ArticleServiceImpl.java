package com.example.starter.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starter.dao.ArticleDao;
import com.example.starter.dto.Article;
import com.example.starter.util.CUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleDao articleDao;
	
	//ArticleService는 데이터 관련해서는 모두 Dao에게 위힘
	@Override
	public List<Article> getList(){
		return articleDao.getList();
		
	}

	@Override
	public long add(Map<String, Object> param) {
		articleDao.add(param);
		
		return CUtil.getAsLong(param.get("id"));
		
	}

	@Override
	public int getTotalCount() {
		return articleDao.getTotalCount();
	}

	@Override
	public Article getOne(long id) {
		return articleDao.getOne(id);
	}

	@Override
	public void delete(long id) {
		articleDao.delete(id);
		
	}

	@Override
	public void modify(Map<String, Object> param) {
		articleDao.modify(param);
		
	}

	@Override
	public void hitUp(long id) {
		articleDao.hitUp(id);
		
	}
}
