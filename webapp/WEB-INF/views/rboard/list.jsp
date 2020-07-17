<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/boardAside.jsp"></c:import>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="${pageContext.request.contextPath}/rboard/search" method="get">
						<div class="form-group text-right">
							<input type="text" name="keyword">
							<input type="hidden" name="page" value="1">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${rv}" var="rv" varStatus = "status">
							<tr>
								<td>${rv.no}</td>
								<td class="text-left">
								<c:forEach begin="1" end="${rv.depth }">&nbsp;&nbsp;</c:forEach>
								<a href="${pageContext.request.contextPath}/rboard/read?no=${rv.no}">${rv.title}</a></td>
								<td>${rv.name}</td>
								<td>${rv.hit}</td>
								<td>${rv.reg_date}</td>
								
								<td>
								<c:if test="${sessionScope.authUser.no == rv.user_no }">
								<a href="${pageContext.request.contextPath}/rboard/delete?no=${rv.no}">[삭제]</a>
								</c:if>
								</td>
								
							</tr>
						</c:forEach>
						</tbody>
					</table>
		
					<div id="paging">
						<ul>
							<li><a href="${pageContext.request.contextPath}/rboard/list?page=${page}-1">◀</a></li>
							
							<c:forEach var="page" begin="1" end="${p.count}">

								<li <c:if test="${param.page eq page}"> class="active" </c:if>>
									<a
									href="${pageContext.request.contextPath}/rboard/list?page=${page}">${page}</a>
								</li>

							</c:forEach>
							<li><a href="${pageContext.request.contextPath}/rboard/list?page=${page}+1">▶</a></li>
						</ul>
						
						
						<div class="clear"></div>
					</div>
					
					<c:if test="${!empty sessionScope.authUser }">
					<a id="btn_write" href="${pageContext.request.contextPath}/rboard/writeForm">글쓰기</a>
					</c:if>
					
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
