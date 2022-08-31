<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CGV</title>
<link rel="stylesheet"  href="http://localhost:9000/mycgv/resources/css/mycgv.css">
</head>
<body>
	<!-- Header Include -->
	<iframe src="http://localhost:9000/mycgv/header.do" width="100%" height="160px" scrolling="no" frameborder=0 ></iframe>
	
	
	<!---------------------------------------------->
	<!--------------- Content ----------------------->
	<!---------------------------------------------->
	<div class="content">
		<h1>회원정보-상세보기</h1>
		<table class="boardContent">	
			<tr>				
				<th>아이디</th>
				<td>${ vo.id }</td>
				<th>성명</th>
				<td>${ vo.name }</td>
				<th>가입날짜</th>
				<td>${ vo.mdate }</td>
			</tr>		
			<tr>				
				<th>주소</th>
				<td colspan="5">${ address }</td>
			</tr>
			<tr>				
				<th>연락처</th>
				<td colspan="5">${ vo.pnumber }</td>
			</tr>
	 	 	<tr>				
				<th>취미</th>
				<td colspan="5">${ vo.hobbylist }</td>
			</tr> 
			<tr>				
				<th>자기소개</th>
				<td colspan="5">${ vo.intro }</td>
			</tr>
			<tr>
				<td colspan="6">					
					<a href="admin_member_list.do"><button type="button" class="btn_style">리스트</button></a>
					<a href="admin.do"><button type="button" class="btn_style">관리자홈</button></a>
				</td>
			</tr>			
		</table>	
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>






