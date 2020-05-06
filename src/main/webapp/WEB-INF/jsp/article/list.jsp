<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.starter.dto.Article"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageName" value="게시물 리스트"/>
<%@ include file="../part/head.jspf" %>

<h2 class="con">전체 게시물 개수 : ${totalCount}</h2>

<div class="con">
	<form action="./list" method="GET">
		<input type="hidden" name="page" value="1">
		<input type="hidden" name="sort" value="${param.sort}">
		<select name="searchType">
			<option value="title">제목</option>
			<option value="body">내용</option>
		</select>
		
		<c:if test="${param.searchType != null && param.searchType != ''}">
			<script>
				$('select[name="searchType"]').val('${param.searchType}');
			</script>
		</c:if>

		<input type="text" name="searchKeyword" placeholder="검색어"
			value="${param.searchKeyword}"> <input type="submit"
			value="검색" /> <br>
	</form>
</div>

<div class="con">
	<select class="sort-select" onchange="Article__sortChanged(this)">
		<option value="latest">최신순</option>
		<option value="old">오래된 순</option>
	</select>
	<c:if test="${param.sort != null && param.sort != ''}">
		<script>
			$('.sort-select').val("${param.sort}");
		</script>
	</c:if>
</div>

<div class="table-box con">
	<table>
		<colgroup>
			<col width="80">
			<col width="80">
			<col width="180">
			<col>
			<col width="100">
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>조회수</th>
				<th>등록날짜</th>
				<th>제목</th>
				<th>댓글</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="article" items="${pagedListRs.list}">
				<tr>
					<td><c:out value="${article.id}" /></td>
					<td><c:out value="${article.hit}" /></td>
					<td><c:out value="${article.regDate}" /></td>
					<td><a href="detail?id=${article.id}"><c:out
								value="${article.title}" /></a></td>
					<td>${article.extra.repliesCount}</td>
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

<div class="con page-nav text-align-center line-height-0-ch-only">
	<ul class="row inline-block">
		<c:forEach var="currentPage" begin="1" end="${pagedListRs.lastPage}">
			<!-- URL 초기값 -->
			<c:url var="url" value="">
				<c:forEach items="${param}" var="entry">
					<c:if test="${entry.key != 'page'}">
						<c:param name="${entry.key}" value="${entry.value}" />
					</c:if>
				</c:forEach>
				<c:param name="page" value="${currentPage}" />
			</c:url>
			<c:set var="aElclass"
				value="${currentPage == pagedListRs.page ? 'red bold' : ''}" />
			<li class="cell"><a href="${url}" class="block ${aElclass}">${currentPage}</a></li>
		</c:forEach>
	</ul>
</div>
<%@ include file="../part/foot.jspf" %>