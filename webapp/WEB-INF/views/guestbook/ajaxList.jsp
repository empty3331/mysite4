<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>      
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/guestAside.jsp"></c:import>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>ajax방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">ajax방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/guest/add" method="get">
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></th>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></th>
								<td><input id="input-pass"type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button type="submit">등록</button></td>
							</tr>
						</tbody>
						
					</table>
					<!-- //guestWrite -->
					
				</form>	
				
				<div id="guestbookListArea"></div>
				
			</div>
			<!-- //guestbook -->
			
			
			
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
$(document).ready(function(){
	//전체리스트 불러오기
	fetchList();
	
});


//전체리스트 불러오기
function fetchList(){
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guest/list",		
		type : "post",
		//contentType : "application/json",
		//data : {name: ”홍길동"},

		dataType : "json",
		success : function(gbVo){
			console.log(gbVo);
			/*성공시 처리해야될 코드 작성*/
			//$("#guestbookListArea").html("");
			for(var i=0; i<gbVo.length; i++){
				render(gbVo[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
}

//리스트 그리기(1개씩)
function render(gbVo) {
	var str = "";
	str +="<table class = 'guestRead'>";
	str +=" <colgroup>";
	str +="  <col style='width: 10%;'>";
	str +="  <col style='width: 40%;'>";
	str +="  <col style='width: 40%;'>";
	str +="  <col style='width: 10%;'>";
	str +=" </colgroup>";
	str +=" <tr>";
	str +="  <td>"+gbVo.no+"</td>";
	str +="  <td>"+gbVo.name+"</td>";
	str +="  <td>"+gbVo.date+"</td>";
	str +="  <td><a href='${pageContext.request.contextPath }/guest/deleteForm?no=$"+gbVo.no+"'>[삭제]</a></td>";
	str +=" </tr>";
	str +=" <tr>";
	str +="  <td colspan=4 class='text-left'>"+gbVo.content+"</td>";
	str +=" </tr>";
	str +="</table>";
	
	$("#guestbookListArea").append(str);
}

</script>


</html>