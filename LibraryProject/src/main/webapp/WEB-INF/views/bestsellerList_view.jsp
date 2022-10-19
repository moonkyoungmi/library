<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- RWD -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- MS -->
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,IE=EmulateIE9"/> 
<title>JSP</title>
<!--bootstrap-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!--jquery 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
-->
<!--propper jquery -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!--latest javascript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<!--fontawesome icon-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<!--google icon -->
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="js/jquery.twbsPagination.js"></script>
<style>
html, body {
	height : 100%,
	margin : 0,
	padding : 0;
}

h3 {
	color : #3f3f48;
}

hr {
	border : 0;
	height : 2px;
	color : #d7d8df;
}
#mythead {
	background-color : #d7d8df;
	color : #3f3f48;
	text-align : center;
}

#booklist_2 {
	color : #3f3f48;
	text-align : center;
}

#booktitle {
	color : #3f3f48;
}

.page-link {
	color : #3f3f48;
	background-color : #ffffff;

}

.page-link:focus {
	background-color : #d7d8df;
	color : #527062;
}

.page-item.active .page-link {
	color : #ececf0;
	background-color : #527062;
	font-weight : bold;
}

#enrollbtn {
	float: right;
	background-color : #527062;
}
</style>
</head>
<body>

<div class="row mt-5">
	<div class="col-2 border">
		<jsp:include page="bookList_menu.jsp" flush="false"/>
	</div>
	<div class="col-10">
		<!-- 베스트셀러게시판 -->
		<div id = "maindiv">
			<h3 id = "newbookh3" class="mb-2" style="color:#527062;"><i class="fas fa-book"></i>&nbsp;<b>베스트셀러</b></h3>
			<hr/>
			<p style="font-size:18px; background:#ececf0;border-radius:20px 40px;" class="p-5">
				<span style="color:#527062;"><b>베스트셀러 소개</b></span><br/>
				전국 서점에서 많이 팔리는 책을 소개합니다.
			</p>
			<div align="right" class="mb-1 mt-5">
				<form action="bestSellerSearch" method="post" id="bestSellerSearch">
					<select name="type">
						<option value="title">제목</option>
						<option value="writer">저자</option>
					</select>
					<input type="text" name="keyword" value="" placeholder="키워드 검색"/>
					<input type="submit" id="bestSellerSearchBtn" value="검색">
				</form>
			</div>
			<div id="bestsellerMain">
				<table class = "table table-hover" id="booklist" style="border-top:2px solid black;border-bottom:2px solid black;">
					<thead id = "mythead">
						<tr>
							<th style="width:15%">분류</th>
							<th style="width:15%">표지</th>
							<th style="width:30%">제목</th>
							<th style="width:15%">저자</th>
							<th style="width:15%">출판사</th>
							<th style="width:10%">출판년도</th>
						</tr>
					</thead>
					<tbody id = "booklist_2">
						<c:forEach items="${bookBestList}" var="dto">
							<tr>
								<td>${dto.genre}</td>
								<td>
									<img src="upimage/${dto.img}" style="width:50%;" class="border border-dark"/>
								</td>
								<td>
									<a href="content_view?bookId=${dto.bookId}" class="bclick" style="color:black;">${dto.bookTitle}</a>
								</td>
								<td>${dto.writer}</td>
								<td>${dto.publisher}</td>
								<td>${dto.year}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<tfoot>
					<tr>
						<td colspan = "6">
							<div align="right">
								<a class="btn" href="bookRegistration" id="bookRegistration" style="background-color:#527062; color:white;"><i class="fas fa-pen"></i>&nbsp;도서 등록</a>
							</div>
						</td>
					</tr>
				</tfoot>
			</sec:authorize>
			<br/>
		</div>
		
		<!-- 페이지 표시 : API 사용 -->
		<nav aria-label="Page navigation"> 
			<ul class="pagination justify-content-center" id="pagination" style="margin:20px 0"></ul>
		</nav>
	</div>
</div>

<script>
$(document).ready(function(){
	$("#bookRegistration").click(function(event){
		event.preventDefault();
		$.ajax({
			url : $("#bookRegistration").attr("href"),
			type : "GET",
			data : "",
			success : function(data){ 
				$("#mainRegion").html(data);
			},
			error : function(){ 
				alert("서버 접속 실패. 다시 시도해주세요.");
			}
		});
	});
	$(document).on('click', '.bclick', function(event){
		event.preventDefault();
		let evo = $(event.target); 
		let url = evo.attr("href");
		$.ajax({
			url : url,
			type : "GET",
			data : "",
			success : function(data){
				$("#mainRegion").html(data);
			},
			error : function(){
				alert("서버 접속 실패. 다시 시도해주세요.");
			}
		});
	});
	
	window.pagObj = $("#pagination").twbsPagination({
		totalPages: 5,
		visiblePages: 5,
		onPageClick : function(event,page) {
			console.info(page + ' (from options)');
			$(".page-link").on("click",function(event){ 
				event.preventDefault();
				let peo = $(event.target);
				let pageNo = peo.text();
				let bestListUrl;
				let pageA;
				let pageNo1;
				let pageNo2;
				if(pageNo != "First" && pageNo != "Previous" && pageNo != "Next" && pageNo != "Last") {
					bestListUrl = "bestListUrl?pageNo=" + pageNo;
				}
				else if(pageNo == "Next") {
					pageA = $("li.active > a");
					pageNo = pageA.text();
					pageNo1 = parseInt(pageNo);
					pageNo2 = pageNo1 + 1;
					bestListUrl = "bestListUrl?pageNo=" + pageNo2; 
				}
				else if(pageNo == "Previous") {
					pageA = $("li.active > a");  
					pageNo = pageA.text(); 
					pageNo1 = parseInt(pageNo);
					pageNo2 = pageNo1 - 1;
					bestListUrl = "bestListUrl?pageNo=" + pageNo2; 
				}
				else if(pageNo == "First") {
					bestListUrl = "bestListUrl?pageNo=" + 1; 
				}
				else if(pageNo == "Last") {
					bestListUrl = "bestListUrl?pageNo=" + 5; 
				}
				else {
					return;
				}
				$.ajax({
					url : bestListUrl,
					type : "GET",
					data : "",
					success : function(data) {
						$("#bestsellerMain").html(data);
						let parent = $(peo).parent(); 
						parent.addClass("active");
					},
					error : function() {
						alert("서버 접속 에러");
					}					
				}); //ajax
			}); //on-click
		} //onPageClick 
	})//window.pagObj
	.on('page', function(event, page){  
		console.info(page + ' (from event listening)');
	});
	
	$("#bestSellerSearch").submit(function(event){
		event.preventDefault();
		$.ajax({
			url : "bestSellerSearch",
			type : "GET",
			data : $("#bestSellerSearch").serialize(),
			success : function(result){
				$("#booklist> tbody").empty();
				if(result.length >= 1) {
					result.forEach(function(item){
						str = "<tr>";
						str += '<td>' + item.genre + '</td>';
						str += '<td><img src="upimage/' + item.img + '" style="width:50%;" class="border border-dark"/></td>';
						str += '<td><a href="content_view?bookId='+ item.bookId + '" class="bclick" style="color:black;">' + item.bookTitle + '</a></td>';
						str += '<td>' + item.writer + '</td>';
						str += '<td>' + item.publisher + '</td>';
						str += '<td>' + item.year + '</td>';
						str += "</tr>";
						$("#booklist").append(str);
					});
				}
			},
			error : function(){
				alert("서버 접속 실패. 다시 시도해주세요.");
			}
		});
	});
});
</script>
</body>
</html>