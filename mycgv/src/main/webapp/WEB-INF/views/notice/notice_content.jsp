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
		<h1>공지사항</h1>
		<table class="boardContent">	
			<tr>				
				<th>등록일자</th>
				<td>2022-07-22</td>
				<th>조회수</th>
				<td>1245</td>
			</tr>		
			<tr>				
				<th>제목</th>
				<td colspan="3">7월 마지막 이벤트 입니다~ ^^</td>
			</tr>
			<tr>				
				<th>내용</th>
				<td colspan="3">탑건 재밌어요~ ^^<br><br><br><br></td>
			</tr>
			<tr>
				<td colspan="4">					
					<a href="notice_list.jsp"><button type="button" class="btn_style">리스트</button></a>
					<a href="http://localhost:9000/mycgv/index.do"><button type="button" class="btn_style">홈으로</button></a>
				</td>
			</tr>			
		</table>	
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







