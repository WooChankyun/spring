package com.mycgv.dao;

import java.util.ArrayList;

import com.mycgv.vo.CgvMemberVO;

public class CgvMemberDAO extends DBConn{
	/**
	 * totalCount : 전체 로우수 출력
	 */
	public int totalCount() {
		int result = 0;
		String sql = "select count(*) from cgv_member";
		
		try {
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			//close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * idCheck : 아이디 중복체크
	 */
	public int idCheck(String id) {
		int result = 0;
		String sql = "select count(id) from cgv_member "
				+ " where id=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * selectContent : 회원 상세 정보
	 */
	public CgvMemberVO selectContent(String id) {
		CgvMemberVO vo = new CgvMemberVO();
		String sql = "select id, name, zonecode, addr1, addr2, pnumber "
				+ " , hobbylist, mdate, intro  "
				+ " from cgv_member where id=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setZonecode(rs.getString(3));
				vo.setAddr1(rs.getString(4));
				vo.setAddr2(rs.getString(5));
				vo.setPnumber(rs.getString(6));
				vo.setHobbylist(rs.getString(7));
				vo.setMdate(rs.getString(8));
				vo.setIntro(rs.getString(9));
			}
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/**
	 * selectAll : 회원 전체 리스트
	 */
	public ArrayList<CgvMemberVO> selectAll(int startCount, int endCount){
		ArrayList<CgvMemberVO> list = new ArrayList<CgvMemberVO>();
		String sql = " select rno, id, name, pnumber, mdate "
				+ " from (select rownum rno, id, name, pnumber, to_char(mdate,'yyyy-mm-dd') mdate " + 
				" from (select id, name, pnumber, mdate from cgv_member " + 
				"            order by mdate desc)) "
				+ " where rno between ? and ?"; 
		
		try {
			getPreparedStatement(sql);
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvMemberVO vo = new CgvMemberVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setPnumber(rs.getString(4));
				vo.setMdate(rs.getString(5));
				
				list.add(vo);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * select : 로그인
	 */
	public int select(CgvMemberVO vo) {
		int result = 0;
		String sql = "select count(*) from cgv_member "
				+ " where id=? and pass=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	/**
	 * insert : 회원가입 
	 */
	public int insert(CgvMemberVO vo) {
		int result = 0;
		String sql = "insert into cgv_member "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZonecode());
			pstmt.setString(7, vo.getAddr1());
			pstmt.setString(8, vo.getAddr2());
			pstmt.setString(9, vo.getHp());
			pstmt.setString(10, vo.getPnumber());
			pstmt.setString(11, vo.getHobbylist());
			pstmt.setString(12, vo.getIntro());
			
			result = pstmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
