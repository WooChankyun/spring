package com.mycgv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {
	//Field
	private String url = "jdbc:oracle:thin:@localhost:1521";
	private String user = "scott";
	private String pass = "tiger";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	//Constructor
	public DBConn() {
		try {
			//0. JDBC 드라이버 준비 - 톰캣의 lib 폴더에 넣어준다!!
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("1. 드라이버 로딩 성공!");
			
			//2. Connection 객체 생성
			conn = DriverManager.getConnection(url, user, pass);
			//System.out.println("2. Connection 객체 성공!");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//Method
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}








