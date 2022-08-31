package com.mycgv.dao;

import java.util.ArrayList;

import com.mycgv.vo.CgvNoticeVO;

public class CgvNoticeDAO extends DBConn{
	/**
	 * totalCount : ��ü �ο�� ���
	 */
	public int totalCount() {
		int result = 0;
		String sql = "select count(*) from cgv_notice";
		
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
	 * delete : �������� ���� 
	 */
	public int delete(String nid) {
		int result = 0;
		String sql = "delete from cgv_notice where nid=?";
						
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, nid);		
			
			result = pstmt.executeUpdate();
			
			close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	/**
	 * update : �������� ������Ʈ 
	 */
	public int update(CgvNoticeVO vo) {
		int result = 0;
		String sql = "update cgv_notice set ntitle=?,ncontent=? "
				+ " where nid=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getNid());
			
			result = pstmt.executeUpdate();
			
			close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	/**
	 * updateHits : ��ȸ�� ������Ʈ 
	 */
	public void updateHits(String nid) {
		String sql = "update cgv_notice set nhits=nhits+1 "
				+ " where nid=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, nid);
			pstmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * select : �������� �󼼺���
	 */
	public CgvNoticeVO select(String nid) {
		CgvNoticeVO vo = new CgvNoticeVO();
		String sql = "select nid,ntitle,ncontent,nhits,ndate, nfile, nsfile "
				+ " from cgv_notice where nid=?";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, nid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setNid(rs.getString(1));
				vo.setNtitle(rs.getString(2));
				vo.setNcontent(rs.getString(3));
				vo.setNhits(rs.getInt(4));
				vo.setNdate(rs.getString(5));
				vo.setNfile(rs.getString(6));
				vo.setNsfile(rs.getString(7));
			}
			
			//close();  //��ȸ�� ó���� Ŀ�ؼ� ���Ḧ �������� �ּ�ó����!!!			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	/**
	 * select : ��ü �������� ����Ʈ
	 */
	public ArrayList<CgvNoticeVO> select(int startCount, int endCount){
		ArrayList<CgvNoticeVO> list = new ArrayList<CgvNoticeVO>();
		String sql = "select rno, nid, ntitle, nhits, ndate "
				+ " from (select rownum rno, nid, ntitle, nhits,to_char(ndate,'yyyy-mm-dd') ndate " + 
				" from (select nid, ntitle, nhits,ndate from cgv_notice " + 
				"            order by ndate desc)) "
				+ " where rno between ? and ? ";
		
		try {
			getPreparedStatement(sql);
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvNoticeVO vo = new CgvNoticeVO();
				vo.setRno(rs.getInt(1));
				vo.setNid(rs.getString(2));
				vo.setNtitle(rs.getString(3));
				vo.setNhits(rs.getInt(4));
				vo.setNdate(rs.getString(5));
				
				list.add(vo);
			}
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * insert : �������� ���
	 */
	public int insert(CgvNoticeVO vo) {
		int result = 0;
		String sql = "insert into cgv_notice "
				+ " values('n_'||sequ_cgv_notice.nextval,?,?,?,?,0,sysdate)";
		
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getNfile());
			pstmt.setString(4, vo.getNsfile());
			
			result = pstmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
