<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sid = (String)session.getAttribute("sid");
	if(sid != null){
		session.invalidate();
	}
%>

<script>
	alert("정상적으로 로그아웃 되었습니다.");
	location.href = "http://localhost:9000/mycgv/index.do";
</script>

