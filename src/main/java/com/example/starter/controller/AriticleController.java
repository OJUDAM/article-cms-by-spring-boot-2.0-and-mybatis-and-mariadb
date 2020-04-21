package com.example.starter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/article/list")
	public String showList(Model aModel) {
		List<Article> list = articleService.getList();
		
		//requet.setAttribute("list", list); 와 똑같은 표현
		aModel.addAttribute("list", list);
		
		//log.info("list : " + list);
		return "article/list";
	}
}
