package DalgoDAO;

import java.sql.Connection;
import java.util.ArrayList;

import DalgoUtill.DalgonaDB;
import DalgoVO.MemberVO;
import DalgoVO.PlanVO;

public class StaffDAO 
{
	Connection conn;
		
	DalgonaDB db;
	public ArrayList<MemberVO> selectStaff()
	{
		db = new DalgonaDB();
		String sql = "";
		db.DBopen();
		
		ArrayList<MemberVO> alist = new ArrayList<MemberVO>();
		

			sql = "SELECT USER_NO,USER_KR,USER_DP,USER_STATE,USER_TYPE FROM USER_INFO "
				+ "ORDER BY USER_DP ASC";	
			db.RunQuery(sql);
		
		
		while(db.GetNext()) 
		{				
			MemberVO mv = new MemberVO();	
			
			String USER_NO = db.GetString("USER_NO");
			if(USER_NO == null)								{mv.setUSER_NO("");}
			else											{mv.setUSER_NO(USER_NO);}
			
			String USER_KR = db.GetString("USER_KR");
			if(USER_KR == null ) 							{mv.setUSER_KR("신원 미등록");}
			else				 							{mv.setUSER_KR(USER_KR);}
			
			String USER_DP = db.GetString("USER_DP");
			if(USER_DP == null ) 							{mv.setUSER_DP("--");}
			else				 							{mv.setUSER_DP(USER_DP);}
			
			String USER_TYPE = db.GetString("USER_TYPE");
			if(USER_TYPE == null || USER_TYPE.equals("I") ) {mv.setUSER_TYPE("신입");}
			else if(USER_TYPE.equals("S"))					{mv.setUSER_TYPE("사원");}
			else if(USER_TYPE.equals("R"))					{mv.setUSER_TYPE("팀장");}
			else if(USER_TYPE.equals("O"))					{mv.setUSER_TYPE("사장");}
			else										    {mv.setUSER_TYPE(USER_DP);}
			
			String USER_STATE = db.GetString("USER_STATE");
			if(USER_STATE == null || USER_STATE.equals("W")) {mv.setUSER_STATE("근무중");}
			else if(USER_STATE.equals("O")) 				 {mv.setUSER_STATE("퇴근");}
			else if(USER_STATE.equals("H")) 				 {mv.setUSER_STATE("병가");}
			else if(USER_STATE.equals("A")) 				 {mv.setUSER_STATE("휴가");}
			else if(USER_STATE.equals("V")) 				 {mv.setUSER_STATE("결근");}
			else											 {mv.setUSER_STATE("--");}
				
			alist.add(mv);

		}			 
		db.DBClose();
		return alist;
	}
	
	public boolean WorkUpdate(String USER_NO, String DP, String TYPE, String STATE) 
	{
		db = new DalgonaDB();
		db.DBopen();
		String sql = "update USER_INFO set USER_DP = ?, USER_TYPE = ?, USER_STATE = ? where USER_NO = ?";
		
		System.out.println(sql);
		System.out.println(USER_NO);
		System.out.println(DP);
		System.out.println(TYPE);
		System.out.println(STATE);
		
		db.RunSQL(sql, DP, TYPE, STATE, USER_NO);
		return true;
	}
	
}
