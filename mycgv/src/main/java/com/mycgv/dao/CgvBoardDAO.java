package com.mycgv.dao;

import java.util.ArrayList;

import com.mycgv.vo.CgvBoardVO;

public class CgvBoardDAO extends DBConn{
	/**
	 * totalCount : 전체 로우수 출력
	 */
	public int totalCount() {
		int result = 0;
		String sql = "select count(*) from cgv_board";
		
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
	 * delete : 게시글 삭제
	 */
	public int delete(String bid) {
		int result = 0;
		String sql = "delete from cgv_board where bid=?";
		
		try {
			getPreparedStatement(sql);  //pstmt 객체 생성
			pstmt.setString(1, bid);
			
			result = pstmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * update : 게시글 수정 
	 */
	public int update(CgvBoardVO vo) {
		int result = 0;
		String sql = "update cgv_board set btitle=?, bcontent=? "
				+ " where bid=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBid());
			
			result = pstmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * updateHits : 조회수 업데이트
	 */
	public int updateHits(String bid) {
		int result = 0;
		String sql = "update cgv_board set bhits=bhits+1 "
				+ " where bid=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			result = pstmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	/**
	 * select : 게시글 상세 보기
	 */
	public CgvBoardVO select(String bid) {
		CgvBoardVO vo = new CgvBoardVO();
		String sql = "select bid, btitle, bcontent, bhits, bdate, bfile, bsfile "
				+ " from cgv_board  where bid=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setBid(rs.getString(1));
				vo.setBtitle(rs.getString(2));
				vo.setBcontent(rs.getString(3));
				vo.setBhits(rs.getInt(4));
				vo.setBdate(rs.getString(5));
				vo.setBfile(rs.getString(6));
				vo.setBsfile(rs.getString(7));
			}
			
			//close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/**
	 * select : 게시글 전체 리스트(페이징처리)
	 */
	public ArrayList<CgvBoardVO> select(int startCount, int endCount){
		ArrayList<CgvBoardVO> list = new ArrayList<CgvBoardVO>();
		String sql = "select rno, bid, btitle, bhits, bdate " +
				 " from (select rownum rno, bid, btitle, bhits, to_char(bdate, 'yyyy-mm-dd') bdate " + 
			" from (select bid, btitle, bhits, bdate from cgv_board " + 
			"            order by bdate desc))" +
			" where rno between  ? and ?"; 
		
		try {
			getPreparedStatement(sql);
			
			pstmt.setInt(1, startCount); 
			pstmt.setInt(2, endCount);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CgvBoardVO vo = new CgvBoardVO();
				vo.setRno(rs.getInt(1));
				vo.setBid(rs.getString(2));
				vo.setBtitle(rs.getString(3));
				vo.setBhits(rs.getInt(4));
				vo.setBdate(rs.getString(5));
				
				list.add(vo);
			}
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * insert : 게시글 등록
	 */
	public int insert(CgvBoardVO vo) {
		int result = 0;
		String sql = "insert into cgv_board "
				+ " values('b_'||sequ_cgv_board.nextval, ?, ?, ?, ?, 0, sysdate)";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBfile());
			pstmt.setString(4, vo.getBsfile());
			
			result = pstmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}



