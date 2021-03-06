package com.example.starter.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.starter.dto.Article;
import com.example.starter.dto.ArticleReply;
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
		List<ArticleReply> articleReplyList = articleService.getReplyList(Maps.of("articleId", id));
		articleService.hitUp(id);
		
		model.addAttribute("article",article);
		model.addAttribute("articleReplyList",articleReplyList);
		
		return "article/detail";
	}
	
	@RequestMapping("/article/modify")
	public String showModify(Model model, long id) {
		Article article = articleService.getOne(id);
		
		model.addAttribute("article",article);
		
		return "article/modify";
	}
	
	@RequestMapping("/article/doModify")
	@ResponseBody
	public String doModify(@RequestParam Map<String, Object> param, long id) {
		articleService.modify(param);
	
		String msg = id+"번 게시물이 수정되었습니다.";
		
		StringBuilder sb = new StringBuilder();

		
		sb.append("alert('" + msg + "');");
		sb.append("location.replace('./detail?id="+id +"');");
		
		sb.insert(0,  "<script>");
		sb.append("</script>");
		
		return sb.toString();
	}

	@RequestMapping("/article/list")
	public String showList(Model model, @RequestParam Map<String, Object> param) {	
		if(param.containsKey("page") == false) {
			param.put("page", "1");
		}
		param.put("extra__repliesCount", true);
		Map<String, Object> pagedListRs = articleService.getPagedList(param);
		
		int totalCount = articleService.getTotalCount();
		
		model.addAttribute("pagedListRs",pagedListRs);
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
	@RequestMapping("/article/doAddReply")
	@ResponseBody
	public String doAddReply(@RequestParam Map<String, Object> param) {
		StringBuilder sb = new StringBuilder();
		articleService.doAddReply(param);
		sb.append("<script>location.replace('./detail?id="+param.get("articleId")+"')</script>");
		
		return sb.toString();
	}
	@RequestMapping("article/doDeleteReply")
	@ResponseBody
	public String doDeleteReply(@RequestParam Map<String,Object> param, @RequestParam(value = "id",defaultValue = "0") int article,
			HttpServletRequest request ) {
		StringBuilder sb = new StringBuilder();
		
		articleService.deleteReply(article);
		
		sb.append("<script>alert('댓글이 삭제되었습니다.');location.replace('"+request.getHeader("REFERER")+"')</script>");
		
		return sb.toString();
	}
	
	@RequestMapping("article/sort")
	public String sort(Model model, @RequestParam Map<String, Object> param) {
		param.put("extra__repliesCount", true);
		param.put("desc_list", true);
		Map<String, Object> pagedListRs = articleService.getPagedList(param);
		
		model.addAttribute("pagedListRs",pagedListRs);
		
		return "article/list";
	}
	
	@RequestMapping("article/oldSort")
	public String oldSort(Model model, @RequestParam Map<String, Object> param) {
		param.put("extra__repliesCount", true);
		param.put("desc_list", false);
		Map<String, Object> pagedListRs = articleService.getPagedList(param);
		
		model.addAttribute("pagedListRs",pagedListRs);
		
		return "article/list";
	}
	
}
