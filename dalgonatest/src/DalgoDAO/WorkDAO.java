package DalgoDAO;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import DalgoUtill.DalgonaDB;
import DalgoVO.WorkVO;

public class WorkDAO 
{
	Connection conn;
	
	//========================== 이력 보기 ========================
	//DBOpen
	DalgonaDB db;
	String sql = "";
	WorkVO wvo;
	
	public ArrayList<WorkVO> selectCommute(String USER_TYPE, int USER_NO)
	{
		db = new DalgonaDB();
		db.DBopen();
		
		ArrayList<WorkVO> alist = new ArrayList<WorkVO>();
		
		if(USER_TYPE == "O")
		{
			sql = "SELECT A.CO_NO, A.CO_CI, A.CO_NOTE, A.CO_CI, to_char(A.CO_START,'yyyy-mm-dd') AS Co_Md, to_char(A.CO_START,'hh24:mi:ss') AS CO_START, to_char(A.CO_END,'(mm-dd) hh24:mi:ss') AS CO_END, B.USER_KR FROM COMMUTE A, USER_INFO B "
				+ "WHERE A.USER_NO = B.USER_NO "
				+ "ORDER BY A.CO_NO ASC";
			db.RunQuery(sql);
		} 
		else
		{
			sql = "SELECT A.CO_NO, A.CO_CI, A.CO_NOTE, to_char(A.CO_START,'yyyy-mm-dd') AS Co_Md, to_char(A.CO_START,'hh24:mi:ss') AS CO_START, to_char(A.CO_END,'(mm-dd) hh24:mi:ss') AS CO_END, B.USER_KR FROM COMMUTE A, USER_INFO B "
				+ "WHERE A.USER_NO = B.USER_NO and A.USER_NO = ? "
				+ "ORDER BY CO_NO ASC";
			db.RunQuery(sql, USER_NO);
		}
		
		
		while(db.GetNext()) 
		{				
			WorkVO cv 	   = new WorkVO();	
			String User_kr = db.GetString("USER_KR");
			if(User_kr == null ) {cv.setUser_kr("불명");}
			else				 {cv.setUser_kr(User_kr);}
			
			String Co_Md = db.GetString("Co_Md");
			if(Co_Md == null )	 {cv.setCo_Md("날짜 불명");}
			else				 {cv.setCo_Md(Co_Md);}

			String Co_Start = db.GetString("CO_START");
			if(Co_Start == null) {cv.setCo_Start("기록없음");}
			else				 {cv.setCo_Start(Co_Start);}

			String co_End = db.GetString("CO_END");
			if(co_End == null)   {cv.setCo_End("미퇴근");}
			else				 {cv.setCo_End(co_End);}
			
			String co_note = db.GetString("CO_NOTE");
			if(co_note == null)  {cv.setCo_note("");}
			else				 {cv.setCo_note(co_note);}
			
			cv.setCo_Ci(db.GetString("Co_Ci"));
			cv.setCo_No(db.GetString("CO_NO"));
			alist.add(cv);

		}			 
		db.DBClose();
		return alist;
	}
	
	//========================== 출퇴근 리스트 ========================
	//출근
	public boolean Staff_Gooffice(String uno){
		db  = new DalgonaDB();
		wvo = new WorkVO(); 
		//출근
		db.DBopen();

		sql = "insert into COMMUTE (CO_NO, User_No, Co_Start) values(COMM_AUTO.NEXTVAL, ?, sysdate)";
		db.RunSQL(sql, uno);
		Last_No();
		db.DBClose();
		//PrintWriter out = new PrintWriter(out);
		//out.print("001");
		return true;
	}
	
	public boolean Last_No()
	{
		wvo = new WorkVO();
		db = new DalgonaDB();
		db.DBopen();
		sql = "SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'COMM_AUTO'";
		//String sql2 = "SELECT COMM_AUTO.CURRVAL FROM DUAL";
		db.RunQuery(sql);
		
		if(db.GetNext() == false)
		{
			db.DBClose();
			return false;
		}
		 int lastno = db.GetInt("LAST_NUMBER");
		 lastno = lastno - 1;
		
		wvo.setCo_No(Integer.toString(lastno));
		
		db.DBClose();
		return true;
	}

	//퇴근
	public boolean Staff_Gohome(String USER_TYPE, String uno) {
		db = new DalgonaDB();
		
		
		// 라스트 등록 번호를 얻고 그 번호에 대한 값을 UPdate 2022 02 21에 만들기  
		WorkDAO dao = new WorkDAO();
		ArrayList<WorkVO> alist = dao.selectCommute(USER_TYPE, Integer.parseInt(uno));
		db.DBopen();
		for (WorkVO wv : alist) {
		// for (int i =0; i< alist.length : i++)
			
			if( wv.getCo_Ci().equals("Y")) {
				String sql = "Update Commute set Co_Ci = 'N', Co_End = sysdate where User_No = ? and Co_No = ?";
				db.RunSQL(sql, uno, wv.getCo_No());
			}
		}
		db.DBClose();
		return true;
	}

}

