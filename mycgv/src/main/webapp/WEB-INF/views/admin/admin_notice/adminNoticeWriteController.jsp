<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mycgv.vo.CgvNoticeVO"  %>
<%@ page import="com.mycgv.dao.CgvNoticeDAO"  %>
<jsp:useBean id="vo" class="com.mycgv.vo.CgvNoticeVO" />
<jsp:setProperty name="vo" property="*" />
<%
	//DB연동
	CgvNoticeDAO dao = new CgvNoticeDAO();
	int result = dao.insert(vo);
	if(result == 1){
		response.sendRedirect("admin_notice_list.jsp");
	}else{
		response.sendRedirect("../errorPage.jsp");
	}
%>