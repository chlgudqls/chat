package dbconn;

import java.sql.*;

public class DbConn {	
	
	String url="jdbc:mysql://127.0.0.1:3306/boarddb?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
	String userid="root";
	String passwd="ezen";
	Connection conn;	

	public Connection getConnection() {
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn =  DriverManager.getConnection(url, userid, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}				
							
		return conn;
	}

}
