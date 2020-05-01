<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageName" value="게시물 리스트"/>
<%@ include file="../part/head.jspf" %>

<h2 class="con">전체 게시물 개수 : ${totalCount}</h2>
<div class="table-box con">
	<table>
		<colgroup>
			<col width="80">
			<col width="80">
			<col>
			<col width="200">
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>조회수</th>
				<th>제목</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="article">
				<tr>
					<td>${article.id}</td>
					<td>${article.hit}</td>
					<td>
						<a href="./detail?id=${article.id}">
						${article.title}
						</a>
					</td>
					<td>${article.regDate}</td>			
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%-- <div class="con">
	<c:forEach items="${list}" var="article">
		<section>번호 : ${article.id}, 제목 : ${article.title}, </section>
		<section>
			<a href="./detail?id=${article.id}">번호 : ${article.id}, 제목 :
				${article.title}, 조회수 : ${article.hit}</a>
		</section>
		<hr>
	</c:forEach>
</div> --%>

<div class="btns con">
	<a href="./add">게시물 추가</a>
</div>

<%@ include file="../part/foot.jspf" %>