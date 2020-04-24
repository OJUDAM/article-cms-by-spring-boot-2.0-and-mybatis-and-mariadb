package com.example.starter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.starter.dto.Article;
import com.example.starter.service.ArticleService;

import jline.internal.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AriticleController {
	@Autowired
	ArticleService articleService;
	
	@RequestMapping("/article/detail")
	public String showDetail(Model model, long id) {
		Article article = articleService.getOne(id);
		
		model.addAttribute("article",article);
		
		return "article/detail";
	}
	
	@RequestMapping("/article/modify")
	public String showModify(Model model, long id) {
		Article article = articleService.getOne(id);
		
		model.addAttribute("article",article);
		
		return "article/modify";
	}
	
	@RequestMapping("/article/list")
	public String showList(Model model) {	

		List<Article> list = articleService.getList();
		int totalCount = articleService.getTotalCount();
		
		model.addAttribute("list",list);
		model.addAttribute("totalCount",totalCount);
		
		return "article/list";
	}
	
	@RequestMapping("/article/add")
	public String showAdd() {

		return "article/add";
	}
	
	@RequestMapping("/article/doAdd")
	@ResponseBody
	public String doAdd(@RequestParam Map<String, Object> param) {
		long newId = articleService.add(param);
		/* 매개변수 doAdd(String title, String body)대신 */
		//articleService.add(param);
		String msg = newId+"번 게시물이 추가되었습니다.";
		
		StringBuilder sb = new StringBuilder();

		//sb.append("<script>");
		sb.append("alert('" + msg + "');");
		sb.append("location.replace('./detail?id="+newId +"');");
		
		sb.insert(0,  "<script>");
		sb.append("</script>");
		
		return sb.toString();
	}
	
	@RequestMapping("/article/doDelete")
	@ResponseBody
	public String doDelete(long id) {
		articleService.delete(id);
		
		String msg = id + "번 게시물이 삭제되었습니다.";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("alert('"+msg +"');");
		sb.append("location.replace('./list');");
		
		sb.insert(0,  "<script>");
		sb.append("</script>");
		return sb.toString();
	}
}
