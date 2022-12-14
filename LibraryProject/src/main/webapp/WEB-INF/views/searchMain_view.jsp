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
	height : 100%;
	margin : 0;
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

#booklist_1 {
	color : #3f3f48;
	text-align : center;
}
</style>
</head>
<body>

<div class = "row mt-5">
	<div class = "col-2 border">
		<jsp:include page = "bookList_menu.jsp" flush = "false"/>
	</div>
	<div class = "col-10">
		<!-- ??????????????? -->
		<div id = "maindiv">
			<h3 id = "newbookh3" class = "mb-2"><i class = "fas fa-book" style = "color:#527062;"></i>&nbsp;???????????? ??????</h3>
			<hr/>
			<div id = "searchtablemain">
				<table class = "table table-hover" id = "booklist" style="border-top:2px solid black;border-bottom:2px solid black;">
					<thead id = "mythead">
						<tr>
							<th style="width:15%">??????</th>
							<th style="width:15%">??????</th>
							<th style="width:30%">??????</th>
							<th style="width:15%">??????</th>
							<th style="width:15%">?????????</th>
							<th style="width:10%">????????????</th>
						</tr>
					</thead>
					<tbody id = "booklist_1">
						<c:forEach items = "${searchMain}" var = "dto">
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
		</div>
		<!-- ????????? ?????? : API ?????? -->
		<nav aria-label="Page navigation"> 
			<ul class="pagination justify-content-center" id="pagination" style="margin:20px 0"></ul>
		</nav>
	</div>
</div>
<script>
$(document).ready(function(){
	window.pagObj = $("#pagination").twbsPagination({
		totalPages: 30, //??? ????????? ???
		visiblePages: 10, //?????? ????????? ???
		onPageClick : function(event, page) {
			console.info(page + ' (from options)');
			$(".page-link").on("click", function(event){ //?????????page-link??? BS4??? pagination??? ?????? a
				event.preventDefault();
				let seo = $(event.target);
				let pageNo = seo.text();
				let surl;
				let pageA;
				let pageNo1;
				let pageNo2;
				
				if(pageNo != "First" && pageNo != "Previous" && pageNo != "Next" && pageNo != "Last") {
					surl = "searchPage?pageNo=" + pageNo;
				} else if (pageNo == "Next") {
					pageA = $("li.active > a"); // li??? active ???????????? ??????, a??? page ????????? ?????? 
					pageNo = pageA.text();		// ????????? ??????
					pageNo1 = parseInt(pageNo); // ????????? ????????? 1????????? ????????? ?????? ????????????
					pageNo2 = pageNo1 + 1;
					surl = "searchPage?pageNo=" + pageNo2;
				} else if (pageNo == "Previous") {
					pageA = $("li.active > a"); // li??? active ???????????? ??????, a??? page ????????? ?????? 
					pageNo = pageA.text();		// ????????? ??????
					pageNo1 = parseInt(pageNo); // ????????? ????????? 1 ?????? ????????? ?????? ????????????
					pageNo2 = pageNo1 - 1;
					surl = "searchPage?pageNo=" + pageNo2;
				} else if (pageNo == "First") {
					surl = "searchPage?pageNo=" + 1;
				} else if (pageNo == "Last"){
					surl = "searchPage?pageNo=" + 30;
				} else {
					return;
				}
				$.ajax({
					url : surl,
					type : "get",
					data : "",
					success : function(data) {
						$("#searchtablemain").html(data);
						let parent = seo.parent(); //page-link??? ????????? page-item??? ??????
						parent.addClass("active");
					},
					error : function() {
						alert("?????? ?????? ??????");
					}					
				}); //ajax
			}); //on-click
		} //onPageClick 
	}).on('page', function(event, page) {
		console.info(page + ' (from event listening)');
	}); 
	
	// ???????????? ????????????
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
				alert("?????? ?????? ??????. ?????? ??????????????????.");
			}
		});
	});
});
</script>
</body>
</html>