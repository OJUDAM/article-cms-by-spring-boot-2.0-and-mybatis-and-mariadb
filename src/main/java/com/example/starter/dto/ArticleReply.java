package com.example.starter.dto;

import java.util.Map;

import org.springframework.web.util.HtmlUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ArticleReply {
	private long id;
	private String regDate;
	private long articleId;
	private long boardId;
	private long memberId;
	private String body;
	
	private Map<String, String> extra;
	
	public String getBodyForPrint() {
		String bodyForPrint=HtmlUtils.htmlEscape(body);
		bodyForPrint = bodyForPrint.replace("\n", "<br>");
		
		return bodyForPrint;
	}
}
