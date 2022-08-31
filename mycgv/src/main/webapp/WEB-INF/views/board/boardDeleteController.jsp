<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv.dao.CgvBoardDAO" %>
<%
	String bid = request.getParameter("bid");
	CgvBoardDAO dao = new CgvBoardDAO();
	int result = dao.delete(bid);
	if(result ==1){
		response.sendRedirect("board_list.jsp");
	}else{
		response.sendRedirect("../errorPage.jsp");
	}
	
%>