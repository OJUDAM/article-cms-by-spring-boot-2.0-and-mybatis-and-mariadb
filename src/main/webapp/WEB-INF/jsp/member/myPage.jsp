<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.starter.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../part/head.jspf"%>

<h3 class="con">마이페이지 입니다.</h3>
<div class="table-common con">
	<table>
		<tbody>
			<%-- <tr>
				<th>이름</th>
				<td><c:out value="${loginedMember.name}" /></td>
			</tr> --%>
			<tr>
				<th>아이디</th>
				<td><c:out value="${loginedMember.loginId}" /></td>
			</tr>
			<tr>
				<th>가입날짜</th>
				<td><c:out value="${loginedMember.regDate}" /></td>
			</tr>
		</tbody>
	</table>
</div>


<%@ include file="../part/foot.jspf"%>