package DalgoDAO;

import java.util.ArrayList;

import DalgoUtill.DalgonaDB;
import DalgoVO.ChatVO;

public class ChatDAO {
	DalgonaDB ddb;
	String 	  sql;
	
	public boolean ChatInsert(ChatVO cvo) {
		ddb = new DalgonaDB();
		ddb.DBopen();
		sql = "insert into Chat ";
		sql += "(User_No, Chat_NO, Chat_NOTE, User_KR, Chat_DATE) ";
		sql += "values(? ,Chat_Auto.NEXTVAL, ?, ?, ?)";
		System.out.println(sql);
		System.out.println(cvo.getUSER_NO()+ cvo.getChat_NOTE()+ cvo.getUSER_KR()+ cvo.getChat_DATE());
		ddb.RunSQL(sql,cvo.getUSER_NO(), cvo.getChat_NOTE(), cvo.getUSER_KR(), cvo.getChat_DATE());
		ddb.DBClose();
		return true;
	}
  
  public ArrayList<ChatVO> ChatSelect(String no) {
	  	ArrayList<ChatVO> list = new ArrayList<ChatVO>();
	  	ddb = new DalgonaDB();
	  	ddb.DBopen();
	  	sql = "select User_No, Chat_NOTE,Chat_NO from Chat where User_No = ? order by Chat_No asc";
	  	ddb.RunQuery(sql, Integer.parseInt(no));
	  	while(ddb.GetNext() == true ) 
	  	{
	  		ChatVO cvo = new ChatVO();
	  		cvo.setChat_NOTE(ddb.GetString("Chat_NOTE"));
	  		cvo.setChat_NO(ddb.GetString("Chat_NO"));
	  		cvo.setUSER_NO(ddb.GetString("User_No"));
	  		list.add(cvo);
	  	} 
	  	ddb.DBClose();
	  	return list;
  }
}
