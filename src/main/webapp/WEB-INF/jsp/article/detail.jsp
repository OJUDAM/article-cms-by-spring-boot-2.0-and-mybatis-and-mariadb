<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.starter.dto.Article"%>
<%@ page import="com.example.starter.dto.ArticleReply"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageName" value="게시물 상세페이지"/>
<%@ include file="../part/head.jspf" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/main/resources/static/resources/main.js"></script>

	<%-- <section class="con">
		번호 : ${article.id}<br>
		제목 : ${article.title}<br>
		내용 : ${article.body}
		조회수 : ${article.hit}
	</section> --%>
	
	<div class="article-detail table-common con">
		<a href="./list">게시물 리스트</a>
		<a href="./add">게시물 추가</a>
		<a href="./modify?id=${article.id}">게시물 수정</a>
		<a onclick="if ( confirm('삭제하시겠습니까?') == false) return flase;" href="./doDelete?id=${article.id}">게시물 삭제</a>
	
		<table>
			<colgroup>
				<col width="80">
			</colgroup>
			<tbody>
				<tr>
					<th>ID</th>
					<td><c:out value="${article.id}"/></td>
				</tr>
				<tr>
					<th>날짜</th>
					<td><c:out value="${article.regDate}"/></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td><c:out value="${article.hit}"/></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><c:out value="${article.title}" escapeXml="true"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${article.bodyForPrint}</td>
				</tr>
			</tbody>
		</table>
		
		<h2>댓글 작성</h2>
		<form action="./doAddReply" class="text-send-reply">
			<input type="hidden" name="articleId" value="${article.id}">
			<div>
				<textarea name="body" placeholder="내용"></textarea>
			</div>
			<div>
				<input type="submit" value="댓글 작성">
				<input type="reset" value="취소">
			</div>
		</form>
		
		<h2>댓글 리스트</h2>
		
		<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>내용</th>
				<th>비고</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="reply" items="${articleReplyList}">
					<tr>
						<td><c:out value="${reply.id}"/></td>
						<td><c:out value="${reply.regDate}"/></td>
						<td><c:out value="${reply.body}"/></td>
						<td><a href="./doDeleteReply?id=${reply.id}">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../part/foot.jspf"%>