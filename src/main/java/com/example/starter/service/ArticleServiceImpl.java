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
		
		/*
		 * //중간확인용 Article article1 = new Article(1, "2019-08-20 12:12:12","제목1","내용1");
		 * Article article2 = new Article(2, "2019-08-20 12:12:13","제목2","내용2"); Article
		 * article3 = new Article(3, "2019-08-20 12:12:14","제목3","내용3");
		 * 
		 * List<Article> list = new ArrayList<>();
		 * 
		 * list.add(article3); list.add(article2); list.add(article1);
		 * 
		 * return list;
		 */
	}

	@Override
	public long add(Map<String, Object> param) {
		// TODO Auto-generated method stub
		articleDao.add(param);
		
		return CUtil.getAsLong(param.get("id"));
		/*
		 * BigInteger bigIntId = (BigInteger)param.get("id"); long newId =
		 * bigIntId.longValue(); //log.info("new Id : " + param.get("id")); return
		 * newId;
		 */
	}

	@Override
	public int getTotalCount() {
		return articleDao.getTotalCount();
	}

	@Override
	public Article getOne(long id) {
		// TODO Auto-generated method stub
		return articleDao.getOne(id);
	}
}
