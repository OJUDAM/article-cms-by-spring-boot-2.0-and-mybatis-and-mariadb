<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.starter.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../part/head.jspf"%>

<script>
	function submitChangeForm(form){
		form.loginId.value = form.loginId.value.trim();
		if(form.loginId.value.length == 0){
			alert('로그인 ID를 입력해주세요.');
			form.loginId.focus();

			return false;
		}

		form.loginPw.value = form.loginPw.value.trim();
		if(form.loginPw.value.length == 0){
			alert('로그인 PW를 입력해주세요.');
			form.loginPw.focus();

			return false;
		}

		form.submit();
	}
</script>

<h3 class="con">회원정보수정</h3>
<div class="table-common con">
<form action="./doChange" method="POST"
	  onsubmit="submitChangeForm(this); return false;">
	<input type="hidden" name="id" value="${loginedMember.id}"> 
	<table>
		<tbody>
			<tr>
				<th>아이디</th>
				<td><input type="text" autofocus="autofocus" name="loginId" value="<c:out value="${loginedMember.loginId}" />"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" autofocus="autofocus" name="loginPw" value="<c:out value="${loginedMember.loginPw}" />"></td>
			</tr>			
		</tbody>
	</table>
	<input type="submit" value="완료">
	</form>
</div>	


<%@ include file="../part/foot.jspf"%>
