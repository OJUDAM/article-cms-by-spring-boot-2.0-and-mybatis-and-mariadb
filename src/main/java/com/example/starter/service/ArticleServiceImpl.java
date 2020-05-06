package com.example.starter.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starter.dao.ArticleDao;
import com.example.starter.dto.Article;
import com.example.starter.dto.ArticleReply;
import com.example.starter.util.CUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
	private static final int LIST_ITEMS_COUNT_IN_A_PAGE= 5;
	
	private List<Article> _getList(Map<String, Object> args){
		int page = 1;
		if(args.containsKey("page")) {
			String pageStr = (String) args.get("page");
			page=Integer.parseInt(pageStr);
		}
		
		if(args.containsKey("extra__repliesCount")&& (boolean)args.containsKey("extra__repliesCount")==true) {
			args.put("leftJoin__articleReply", true);
			args.put("groupBy__articleId", true);
		}
		
		args.put("limitOffset", LIST_ITEMS_COUNT_IN_A_PAGE * (page - 1));
		args.put("limit", LIST_ITEMS_COUNT_IN_A_PAGE);

		return articleDao.getList(args);
	}
	
	private int _getCount(Map<String, Object> args) {
		return articleDao.getCount(args);
	}
	
	
	@Autowired
	ArticleDao articleDao;
	
	@Override
	public List<Article> getList(Map<String, Object> args) {
		System.out.println("dddddd===========================" + args);
		if (args.containsKey("extra__repliesCount") && (boolean) args.containsKey("extra__repliesCount") == true) {
			args.put("leftJoin__articleReply", true);
			args.put("groupBy__articleId", true);
		}
		return _getList(args);
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

	@Override
	public void update(Map<String, Object> args) {
		articleDao.update(args);
	}

	@Override
	public List<ArticleReply> getReplyList(Map<String, Object> args) {
		return articleDao.getReplyList(args);
	}

	@Override
	public long deleteReply(int args) {
		return articleDao.deleteReply(args);
	}

	@Override
	public long doAddReply(Map<String, Object> args) {
		articleDao.doAddReply(args);
		
		return CUtil.getAsLong(args.get("id"));
	}

	@Override
	public void oldSort(Map<String, Object> args) {
		articleDao.oldSort(args);
	}

	@Override
	public List<Article> sort() {
		return articleDao.sort();
	}

	@Override
	public Map<String, Object> getPagedList(Map<String, Object> param) {
		Map<String, Object> rs = new HashMap<>();
		
		int totalItemsCount = _getCount(param);
		
		int lastPage = (int) Math.ceil(totalItemsCount / (double)ArticleServiceImpl.LIST_ITEMS_COUNT_IN_A_PAGE);
		
		rs.put("page", CUtil.getAsInt(param.get("page")));
		rs.put("lastPage", lastPage);
		
		List<Article>list = _getList(param);
		
		rs.put("list",list);
		
		log.info("rs : "+ rs);
		return rs;
	}

	@Override
	public int getCount(Map<String, Object> args) {
		return _getCount(args);
	}

	@Override
	public void change(Map<String, Object> param) {
		articleDao.change(param);
		
	}
}
