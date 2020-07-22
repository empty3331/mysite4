<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
						<c:if test="${!empty sessionScope.authUser }">
						<button id="btnImgUpload">이미지올리기</button>
						</c:if>
						
						<div class="clear"></div>

			
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
						<c:forEach items="${gaVo }" var="gaVo" >
							<li id="img-${gaVo.no}">							
								<div class="view" data-no="${gaVo.no }">
									<img class="imgItem" src="${pageContext.request.contextPath }/upload/${gaVo.saveName }">
									<div class="imgWriter">작성자: <strong>${gaVo.name }</strong></div>
								</div>
							</li>
							
							</c:forEach>
						<!-- 이미지반복영역 -->
						
						
					</ul>
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

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath }/gallery/upload" enctype="multipart/form-data" >
				<input type="hidden" name="user_no" value="${sessionScope.authUser.no }">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="${saveName.content}" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="${saveName.file}">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" height="600px" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
				</div>
				<form method="" action="">
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel" data-no="">삭제</button>
					
				</div>
					<input type="hidden" name="no" value="${no.no }" id="delNo">
					<input type="hidden" name="uname1" id="uname1" value="${sessionScope.authUser.no }">
				
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">


//업로드 모달창 열기
$("#btnImgUpload").on("click",function(){
	//이벤트 체크
	console.log("업로드 모달창");
	$("#addModal").modal();
});

//이미지보기 모달창
$(".view").on("click",function(){
	//이벤트체크
	console.log("상세보기모달");
	$("#viewModal").modal();
	
	//데이터 수집
	var no = $(this).data("no");
	$("#delNo").val(no);
	
	console.log(no);
	console.log($("#uname1").val());
	
	
	//데이터전송
	$.ajax({

			url : "${pageContext.request.contextPath }/gallery/read",
			type : "post",
			//contentType : "application/json",
			data : {no: no},

			dataType : "json",
			success : function(no) {
				console.log(no);
				/*성공시 처리해야될 코드 작성*/
				var imgurl = "/mysite4/upload/"+no.saveName;
				console.log(imgurl);
				
				//이미지 출력
				$("#viewModelImg").attr("src", imgurl);
				
				//코멘트 출력
				$("#viewModelContent").html(no.content);
				
				//버튼 출력(자신의 글일때만 삭제버튼 보이게 처리)
				if($("#uname1").val() != no.user_no){
					$("#btnDel").hide();
				}else {
					$("#btnDel").show();
					$("#btnDel").data("no", no);
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	$("#viewModal").modal();
});


//삭제
$("#btnDel").on("click",function(){
	console.log("삭제버튼 ");
	
	//데이터수집
	var delno = $("#btnDel").data("no");
	
	console.log(delno);
	
	//데이터 전송
	$.ajax({
		//보낼 때 옵션
		url : "${pageContext.request.contextPath}/gallery/delete",
		type : "post",
		data : {delno: delno},
		//받을 때 옵션
		dataType : "json",
		success : function(count) {
			console.log(count);
			
			if(count == 1){
				//리스트 지우기
				$("#img-"+delno).remove();
			} 
			
			//모달창 닫기
			$("#viewModal").modal("hide");
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});





</script>




</html>

