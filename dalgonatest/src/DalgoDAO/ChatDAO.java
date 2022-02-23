package DalgoDAO;

import java.util.ArrayList;

import DalgoUtill.DalgonaDB;
import DalgoVO.ChatVO;
import DalgoVO.RoomVO;

public class ChatDAO {
	DalgonaDB ddb;
	String 	  sql;

  public boolean ChatSelect(String uno, String ano) {
	  String rno = null; 
	  System.out.println("��� ���� ��ȣ : " + ano);
	  System.out.println("�� ��ȣ : " + uno);
	  	ddb = new DalgonaDB();
	  	RoomVO rvo = new RoomVO();
	  	ddb.DBopen();
	  	sql = "select Room_No from Room where Part_No = ? and User_NO = ?";
	  	System.out.println(sql);
	  	ddb.RunQuery(sql, ano, uno);
	  	while(ddb.GetNext() == true) {
	  		rvo.setROOM_NO(ddb.GetString("Room_No"));	
	  		rno = rvo.getROOM_NO();
	  	}
	  	
	  	System.out.println("ä�ù� ��ȣ" + rno);
	  	
	  	sql = "select c.User_No, c.Chat_NOTE, c.Chat_NO from Chat c, room r WHERE r.Room_No = ? order by Chat_No ASC";
	  	ddb.RunQuery(sql, Integer.parseInt(rno));
	 
	  	ddb.DBClose();
	  	return true;
  }
  public boolean ChatCreate(String ano, String uno, String title) {
	  RoomVO rvo = new RoomVO();
	// ArrayList<RoomVO> list = new ArrayList<RoomVO>();
			//������ȣ��
		ddb = new DalgonaDB();
		ddb.DBopen();
		sql = "insert into Room values(Room_Auto.NEXTVAL, ?, ?, ?)";
		ddb.RunSQL(sql, ano, title, uno);
		System.out.println("���� �� ���� : " + sql);
		ddb.DBClose();
		return true;
			
  	}
  public boolean ChatInsert(ChatVO cvo) {
	    System.out.println("2222222222222" + cvo);
	    ddb = new DalgonaDB();
	    ddb.DBopen();
	    System.out.println("ChatInsert " + ddb);
	    sql = "insert into Chat";
	    sql = String.valueOf(sql) + "(User_No, Chat_NO, Chat_NOTE, User_KR, Chat_DATE) ";
	    sql = String.valueOf(sql) + "values(? ,Chat_Auto.NEXTVAL, ?, ?, ?)";
	    System.out.println(sql);
	    //System.out.println(cvo.getUSER_NO()+ cvo.getChat_NOTE()+ cvo.getUSER_KR()+ cvo.getChat_DATE());
	    //ddb.RunSQL(sql,cvo.getUSER_NO(), cvo.getChat_NOTE(), cvo.getUSER_KR(), cvo.getChat_DATE());
	    ddb.DBClose();
	    return true;
	  }
  public ArrayList<ChatVO> ChatSelect2(String ano, String uno) 
  {
	  System.out.println("�����ѹ�"+uno);
	 ArrayList<ChatVO> list = new ArrayList<ChatVO>();
	 ddb = new DalgonaDB();
    ddb.DBopen();
    sql = "select c.User_No, c.Chat_NOTE, c.Chat_NO from Chat c, Room r WHERE r.USER_NO  = ? AND r.Part_No = ? order by Chat_No ASC";
    ddb.RunQuery(sql, Integer.parseInt(uno), Integer.parseInt(ano));
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
