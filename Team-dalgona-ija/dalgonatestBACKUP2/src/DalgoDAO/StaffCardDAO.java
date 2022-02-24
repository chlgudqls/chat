package DalgoDAO;


import DalgoUtill.DalgonaDB;
import DalgoVO.MemberVO;

public class StaffCardDAO {
	DalgonaDB ddb;
	String 	  sql;
	
	
	public MemberVO StaffCard(String USER_NO) 
	{
		MemberVO svo = null;
		         ddb = new DalgonaDB();
		
		ddb.DBopen();
		sql  = "select User_No, USER_CAR, USER_KR, USER_CH, USER_PNO, USER_GEN, USER_KIND, USER_TYPE, ";
		sql += "USER_ADD, USER_CM, USER_KG, USER_SH, USER_BT, USER_COLOR, USER_FS, USER_LIA, USER_LIB, USER_CAA, USER_CAB ";
		sql += "from User_Info ";
		sql += "Where USER_NO = ?";
		ddb.RunQuery(sql, Integer.parseInt(USER_NO));
		
		if(ddb.GetNext() == false) 
		{
			ddb.DBClose();
			return svo;
		}
		svo = new MemberVO();
		svo.setUSER_NO(ddb.GetString("User_No"));
		svo.setUSER_KR(ddb.GetString("USER_KR"));
		svo.setUSER_CH(ddb.GetString("USER_CH"));
		svo.setUSER_PNO(ddb.GetString("USER_PNO"));
		svo.setUSER_GEN(ddb.GetString("USER_GEN"));
		svo.setUSER_KIND(ddb.GetString("USER_KIND"));
		svo.setUSER_ADD(ddb.GetString("USER_ADD"));
		svo.setUSER_TYPE(ddb.GetString("USER_TYPE"));
		svo.setUSER_CM(ddb.GetString("USER_CM"));
		svo.setUSER_KG(ddb.GetString("USER_KG"));
		svo.setUSER_SH(ddb.GetString("USER_SH"));
		svo.setUSER_BT(ddb.GetString("USER_BT"));
		svo.setUSER_COLOR(ddb.GetString("USER_COLOR"));
		svo.setUSER_FS(ddb.GetString("USER_FS"));
		svo.setUSER_CAR(ddb.GetString("USER_CAR"));
		svo.setUSER_CAA(ddb.GetString("USER_CAA"));
		svo.setUSER_CAB(ddb.GetString("USER_CAB"));
		svo.setUSER_LIA(ddb.GetString("USER_LIA"));
		svo.setUSER_LIB(ddb.GetString("USER_LIB"));
		svo.setUSER_TYPE(ddb.GetString("USER_TYPE"));
		
		
		sql  = "select User_No, Pho_No, Pho_Lo, Pho_Ph from Photo ";
		sql += "Where User_No = ?";
		ddb.RunQuery(sql, Integer.parseInt(svo.getUSER_NO()));
		if(ddb.GetNext() == false)
		{
			ddb.DBClose();
			return svo;
		}
		svo.setPho_No(ddb.GetString("Pho_No"));
		svo.setPho_Lo(ddb.GetString("Pho_Lo"));
		svo.setPho_Ph(ddb.GetString("Pho_Ph"));
		
		ddb.DBClose();
		return svo;
	}
}
