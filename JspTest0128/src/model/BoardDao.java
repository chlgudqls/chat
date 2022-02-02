package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DbConn;

public class BoardDao {
	Connection conn;
	DbConn db;
	
	public BoardDao() {		
		db = new DbConn();
		conn = db.getConnection();		
	} 
	
	public int insertBoard(String subject){
		int exec=0;
		
		 String sql = "insert into boardtest(subject) values(?)";
		 try{
		 PreparedStatement pstmt = conn.prepareStatement(sql);
		 pstmt.setString(1, subject);
		 
		 exec = pstmt.executeUpdate();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally {
			 try {
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		 }
		 
		return exec;
	}
	
	
	public ArrayList<BoardVo> selectAllBoard(){
		
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
		
		String sql = "select * from boardtest order by bidx asc";
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo bv = new BoardVo();				
				bv.setBidx(rs.getInt("bidx"));
				bv.setSubject(rs.getString("subject"));
				alist.add(bv);			
			}						
		} catch (SQLException e) {		
			e.printStackTrace();
		}		
		return  alist; 
	}	
	
	public String selectOneBoard(int bidx){
		
		String sql="select subject from boardtest where bidx=?";
		String subject= null;		
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				   subject = rs.getString("subject");			
				}
			
		} catch (SQLException e) {		
			e.printStackTrace();
		}		
		return subject;
	}
	
	
	
	
	
	
	
	
}
