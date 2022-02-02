package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DbConn;

public class MemberDao {
	
	Connection conn;
	DbConn db;
	
	public MemberDao() {		
		db = new DbConn();
		conn = db.getConnection();		
	} 
	
public ArrayList<MemberVo> selectAllMember(){
		
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		
		String sql = "select * from member order by midx asc";
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo mv = new MemberVo();				
				mv.setMidx(rs.getInt("midx"));
				mv.setMemberId(rs.getString("MemberId"));
				mv.setMemberName(rs.getString("memberName"));
				mv.setDelYn(rs.getString("delYn"));
				mv.setWriteday(rs.getString("writeday"));
				alist.add(mv);			
			}						
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}		
		return  alist; 
	}	
	
	public MemberVo loginMember(String memberId, String memberPwd) {
		MemberVo mv = null;
		ResultSet rs = null;
		String sql="select * from member where memberId=? and memberPwd=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rs  = pstmt.executeQuery();
			
			if (rs.next()) {
				mv = new MemberVo();
				mv.setMemberId(rs.getString("memberId"));
				mv.setMidx(rs.getInt("midx"));
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		return mv;
	}

	

}
