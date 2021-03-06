<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">
	<h1>
		<a href="${pageContext.request.contextPath}/main">MySite</a>
	</h1>

	<c:choose>
		<c:when test="${sessionScope.authUser eq null }">
			<ul>
				<li><a href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/joinForm">회원가입</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul>
				<li>${sessionScope.authUser.name }님안녕하세요!</li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/user/modifyForm">회원정보수정</a></li>
			</ul>
		</c:otherwise>
	</c:choose>

</div>
<!-- //header -->

<div id="nav">
	<ul>
		<li><a href="${pageContext.request.contextPath}/guest/addlist">방명록</a></li>
		<li><a href="${pageContext.request.contextPath}/fileupload/form">갤러리</a></li>
		<li><a href="${pageContext.request.contextPath}/board/list?page=1">게시판</a></li>
		<li><a href="">입사지원서</a></li>
	</ul>
	<div class="clear"></div>
</div>
<!-- //nav -->