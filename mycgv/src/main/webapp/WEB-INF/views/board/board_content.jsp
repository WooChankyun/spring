<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<h1>게시판-상세보기</h1>
		<table class="boardContent">	
			<tr>				
				<th>등록일자</th>
				<td>${ vo.bdate } </td>
				<th>조회수</th>
				<td>${ vo.bhits }</td>
			</tr>		
			<tr>				
				<th>제목</th>
				<td colspan="3">${ vo.btitle }</td>
			</tr>
			<tr>				
				<th>내용</th>
				<td colspan="3">${ vo.bcontent }<br><br>
				<c:if test="${ vo.bsfile != null }">
					<!-- upload 폴더의 파일경로를 추가해주세요 -->
					&nbsp;<img src="http://localhost:9000/mycgv/resources/upload/${ vo.bsfile }" height="300">
				</c:if>
				<br><br></td>
			</tr>
			<tr>
				<td colspan="4">
					<a href="board_update.do?bid=${ vo.bid }"><button type="button" class="btn_style">수정하기</button></a>
					<a href="board_delete.do?bid=${ vo.bid }"><button type="button" class="btn_style">삭제하기</button></a>
					<a href="board_list.do">
						<button type="button" class="btn_style">리스트</button>
					</a>
				</td>
			</tr>			
		</table>	
	</div>
	
	<!-- footer Include -->
	<iframe src="http://localhost:9000/mycgv/footer.do" width="100%" height="530px" scrolling="no" frameborder=0></iframe>
	
</body>
</html>







