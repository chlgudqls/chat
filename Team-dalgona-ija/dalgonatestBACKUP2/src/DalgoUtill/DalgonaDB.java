package DalgoUtill;

import java.sql.*;


public class DalgonaDB
{
	String 	   url     		  = "";
	String	   userid  		  = "";
	String     passwd  		  = "";
	Connection conn 		  = null;
	PreparedStatement  pstmt  = null;
	ResultSet          result = null;
	
	public DalgonaDB()	{
		url    = "jdbc:oracle:thin:@192.168.0.134:1521:dalgona";
		userid = "c##dalgona";
		passwd = "dalgona";
	}
	
	
	public boolean DBopen() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,userid,passwd);
		}catch (Exception e) { 
			e.printStackTrace();
		}
		return true;
	}
	
	public void DBClose() {
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean RunCommit() {
		try {
			String sql = "commit";
			
			pstmt = conn.prepareStatement(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	return true;
}
	
	//RUNSQL delete, Update, Insert--------------------------
	public boolean RunSQL(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return true;
	}
	
	public boolean RunSQL(String sql, String x) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, x);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return true;
	}
	public boolean RunSQL(String sql, String x, String y) {
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, x);
			pstmt.setString(2, y);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	public boolean RunSQL(String sql, int x, String y, String z) {
			
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, x);
			pstmt.setString(2, y);
			pstmt.setString(3, z);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean RunSQL(String sql, String x, String y, String z) {
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, x);
			pstmt.setString(2, y);
			pstmt.setString(3, z);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean RunSQL(String sql, String x, String y, String z, String i) {
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, x);
			pstmt.setString(2, y);
			pstmt.setString(3, z);
			pstmt.setString(4, i);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	public boolean RunSQL(String sql, String USER_ID, String USER_PW, String USER_CAR, String USER_KR,
			  String USER_CH, String USER_PNO, String USER_GEN, 
			  String USER_ADD, String USER_CM, String USER_KG, 
			  String USER_SH, String USER_BT, String USER_COLOR ) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1  , USER_ID);
			pstmt.setString(2  , USER_PW);
			pstmt.setString(3  , USER_CAR);
			pstmt.setString(4  , USER_KR);
			pstmt.setString(5  , USER_CH);
			pstmt.setString(6  , USER_PNO);
			pstmt.setString(7  , USER_GEN);
			pstmt.setString(8  , USER_ADD);
			pstmt.setString(9  , USER_CM);
			pstmt.setString(10 , USER_KG);
			pstmt.setString(11 , USER_SH);
			pstmt.setString(12 , USER_BT);
			pstmt.setString(13 , USER_COLOR);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	//RUNSQL delete, Update, Insert--------------------------
	
	
	
	//RunQuery Select----------------------------------------
	public boolean RunQuery(String sql) {
		try {
			
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean RunQuery(String sql, int no) {
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//·Î±×ÀÎ
	public boolean RunQuery(String sql, String x, String y) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, x);
			pstmt.setString(2, y);
			result = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	//RunQuery Select----------------------------------------
	
	public boolean GetNext() {
		try	{
			return result.next();
		}catch(Exception e)	{
			e.printStackTrace();
			return false;
		}
	}
	
	public String GetString(String column) {
		try	{
			return result.getString(column);
		}catch(Exception e)	{
			e.printStackTrace();
			return null;
		}
	}
	
	public int GetInt(int column) {
		try	{
			return result.getInt(column);
		}catch(Exception e)	{
			e.printStackTrace();
			return 0;
		}
	}
	public int GetInt(String column) {
		try	{
			return result.getInt(column);
		}catch(Exception e)	{
			e.printStackTrace();
			return 0;
		}
	}
	
}
