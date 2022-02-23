package DalgoDAO;

import java.sql.*;
import java.util.ArrayList;

import DalgoUtill.DalgonaDB;
import DalgoVO.PlanVO;

public class PlanDAO {

	Connection conn;
	DalgonaDB ddb = new DalgonaDB();
	
	//=====================================플랜 삽입=====================================
	public boolean WorkInsert(PlanVO wvo) {
		
		ddb.DBopen();
		String sql  = "insert into Workplan(User_No, Wp_No, Wp_Note, Wp_StartDate, Wp_EndDate)";
			   sql += "values('" + wvo.getUser_No() + "', Plan_Auto.NEXTVAL, ?, ?, ?)";
		ddb.RunSQL(sql, wvo.getWp_Note(), wvo.getWp_StartDate(), wvo.getWp_EndDate());
		ddb.RunCommit();
		ddb.DBClose();
		return true;
	}
	//=====================================플랜 삽입=====================================
	
	//=====================================플랜 검색=====================================
	public ArrayList<PlanVO> WorkSearch(String no) {
		ArrayList<PlanVO> list = new ArrayList<PlanVO>();
		ddb.DBopen();
		String sql = "select * from Workplan where User_No = ? order by WP_STARTDATE, Wp_No";
		ddb.RunQuery(sql,Integer.parseInt(no));
		while(ddb.GetNext()) {		
			PlanVO wvo = new PlanVO();
			wvo.setUser_No(no);
			wvo.setWp_No(ddb.GetString("Wp_No"));
			wvo.setWp_Note(ddb.GetString("Wp_Note"));
			
			if(ddb.GetString("Wp_StartDate") == null) {
				wvo.setWp_StartDate("");
			}
			else{
				String a = ddb.GetString("Wp_StartDate").replace("0", "").replace(":","");
				wvo.setWp_StartDate(a);
				
			}
			if(ddb.GetString("Wp_EndDate") == null) {
				wvo.setWp_EndDate("");
			}
			else{
				String a = ddb.GetString("Wp_EndDate").replace("0", "").replace(":","");
				wvo.setWp_EndDate(a);
			}
			list.add(wvo);
		}		
		ddb.DBClose();
		return list;
	}
	//=====================================플랜 검색=====================================
											
	//=====================================플랜 삭제=====================================
	public boolean WorkDelete(String No) {
		//DBOpen
		ddb.DBopen();
		String sql = "delete from Workplan where Wp_No=? ";		
		ddb.RunSQL(sql, No);
		return true;
			
	}
	//=====================================플랜 삭제=====================================
		
	//=====================================플랜 전체 삭제=====================================
	public boolean WorkAllDelete(String uvo) {
		//DBOpen
		ddb.DBopen();
		String sql = "delete from Workplan where User_No=?";		
		ddb.RunSQL(sql, uvo);
		return true;
		
	}
	//=====================================플랜 전체 삭제=====================================
		
	//=====================================플랜 업데이트=====================================	
	public boolean WorkUpdate(PlanVO wvo) {
		ddb.DBopen();
		String sql = "update Workplan set Wp_Note = ?, Wp_StartDate = ?, Wp_Enddate = ? where Wp_No = ?";
		ddb.RunSQL(sql, wvo.getWp_No(), wvo.getWp_Note(), wvo.getWp_StartDate(), wvo.getWp_EndDate());
		return true;
	}
}
