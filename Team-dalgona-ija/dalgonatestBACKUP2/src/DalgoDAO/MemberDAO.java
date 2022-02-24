package DalgoDAO;


import DalgoUtill.DalgonaDB;
import DalgoVO.MemberVO;

public class MemberDAO {
	DalgonaDB ddb;
	String 	  sql;
	
	//유저 정보 등록하기
	public boolean UserInsert(MemberVO uvo) {
		ddb = new DalgonaDB();
		ddb.DBopen();
		sql  = "insert into User_Info ";
		sql += "(User_No, USER_ID, USER_PW, USER_CAR, USER_KR, USER_CH, USER_PNO, USER_GEN, USER_ADD, USER_CM, USER_KG, USER_SH, USER_BT, USER_COLOR) ";
		sql += "values(User_Auto.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		ddb.RunSQL(sql, uvo.getUSER_ID(), uvo.getUSER_PW(), uvo.getUSER_CAR(), uvo.getUSER_KR(), uvo.getUSER_CH(), uvo.getUSER_PNO(), uvo.getUSER_GEN(), uvo.getUSER_ADD(), uvo.getUSER_CM(), uvo.getUSER_KG(),	uvo.getUSER_SH(), uvo.getUSER_BT(), uvo.getUSER_COLOR());
		
		//번호를 얻는다
		sql = "SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'USER_AUTO'";
		ddb.RunQuery(sql);
		ddb.GetNext();
		
		int lastno = ddb.GetInt("LAST_NUMBER");
		lastno = lastno - 1;
		uvo.setUSER_NO(Integer.toString(lastno));
		
		//첨부파일을 등록한다.
		sql  = "insert into Photo ";
		sql	+= "( Pho_No, User_No, Pho_Lo, Pho_Ph) " ;
		sql += "values(Photo_Auto.NEXTVAL, ?, ?, ? ) ";
		ddb.RunSQL(sql, uvo.getUSER_NO(), uvo.getPho_Lo(), uvo.getPho_Ph());
		
		ddb.DBClose();
		return  true;
	}
	
	// 아이디 중복 확인하기
	public int UserIdCheck(String USER_ID) {
		MemberVO uvo = new MemberVO();
		ddb          = new DalgonaDB();
		ddb.DBopen();
		sql  = 	"select * from User_Info where USER_ID = '"+ USER_ID +"' ";
		ddb.RunQuery(sql);
		if(ddb.GetNext() == true)
		{
			ddb.DBClose();
			return 1;
		}
		ddb.DBClose();
		return 0;
	}
	
	//유저 정보 보기
	public boolean UserSelect() {
		ddb.DBopen();
		sql = "SELECT * FROM USER_INFO ORDER BY USER_NO ASC ";
		ddb.RunQuery(sql);
		ddb.DBClose();
		
		return true;
	}
	
	//로그인
	public MemberVO MemberLogin(String USER_ID, String USER_PW) {
		MemberVO uvo = null;
		         ddb = new DalgonaDB();
		
		ddb.DBopen();
		sql  = "select User_No, USER_ID, USER_PW, USER_CAR, USER_KR, USER_CH, USER_PNO, USER_GEN, USER_KIND, USER_TYPE, ";
		sql += "USER_ADD, USER_CM, USER_KG, USER_SH, USER_BT, USER_COLOR, USER_FS, USER_LIA, USER_LIB, USER_CAA, USER_CAB ";
		sql += "from User_Info ";
		sql += "Where USER_ID = ? and USER_PW = ?";
		ddb.RunQuery(sql, USER_ID, USER_PW);
		
		if(ddb.GetNext() == false) {
			ddb.DBClose();
			return uvo;
		}
		uvo = new MemberVO();
		uvo.setUSER_NO(ddb.GetString("User_No"));
		uvo.setUSER_ID(ddb.GetString("USER_ID"));
		uvo.setUSER_PW(ddb.GetString("USER_PW"));
		uvo.setUSER_KR(ddb.GetString("USER_KR"));
		uvo.setUSER_CH(ddb.GetString("USER_CH"));
		uvo.setUSER_PNO(ddb.GetString("USER_PNO"));
		uvo.setUSER_GEN(ddb.GetString("USER_GEN"));
		uvo.setUSER_KIND(ddb.GetString("USER_KIND"));
		uvo.setUSER_ADD(ddb.GetString("USER_ADD"));
		uvo.setUSER_CM(ddb.GetString("USER_CM"));
		uvo.setUSER_KG(ddb.GetString("USER_KG"));
		uvo.setUSER_SH(ddb.GetString("USER_SH"));
		uvo.setUSER_BT(ddb.GetString("USER_BT"));
		uvo.setUSER_COLOR(ddb.GetString("USER_COLOR"));
		uvo.setUSER_FS(ddb.GetString("USER_FS"));
		uvo.setUSER_CAR(ddb.GetString("USER_CAR"));
		uvo.setUSER_CAA(ddb.GetString("USER_CAA"));
		uvo.setUSER_CAB(ddb.GetString("USER_CAB"));
		uvo.setUSER_LIA(ddb.GetString("USER_LIA"));
		uvo.setUSER_LIB(ddb.GetString("USER_LIB"));
		uvo.setUSER_TYPE(ddb.GetString("USER_TYPE"));
		
		
		sql  = "select User_No, Pho_No, Pho_Lo, Pho_Ph from Photo ";
		sql += "Where User_No = ?";
		ddb.RunQuery(sql, Integer.parseInt(uvo.getUSER_NO()));
		if(ddb.GetNext() == false)
		{
			ddb.DBClose();
			return uvo;
		}
		uvo.setPho_No(ddb.GetString("Pho_No"));
		uvo.setPho_Lo(ddb.GetString("Pho_Lo"));
		uvo.setPho_Ph(ddb.GetString("Pho_Ph"));
		
		ddb.DBClose();
		return uvo;
	}
}
