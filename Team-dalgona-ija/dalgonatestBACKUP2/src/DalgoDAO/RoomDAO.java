package DalgoDAO;

import java.util.ArrayList;

import DalgoUtill.DalgonaDB;
import DalgoVO.ChatVO;
import DalgoVO.MemberVO;
import DalgoVO.RoomVO;

public class RoomDAO 
{
	String sql;
	DalgonaDB ddb = new DalgonaDB(); 

	
	
	public ArrayList<MemberVO> StaffList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		ddb = new DalgonaDB();
		ddb.DBopen();
		sql = "select User_KR, User_DP, User_No from User_Info  order by User_No ASC";
		System.out.println(sql);
		ddb.RunQuery(sql);
		while(ddb.GetNext() == true)
		{
			MemberVO uvo = new MemberVO();
			uvo.setUSER_KR(ddb.GetString("User_KR"));
			uvo.setUSER_DP(ddb.GetString("User_DP"));
			uvo.setUSER_NO(ddb.GetString("User_No"));
			list.add(uvo);
		}
		ddb.DBClose();
		return list;
	}
	public boolean createRoom(RoomVO rvo) {
		
		// ArrayList<RoomVO> list = new ArrayList<RoomVO>();
		String[] ary = rvo.getRoom_PARTID().split(",");
	
		//유저번호만
		if(ary.length >= 1) {
			ddb = new DalgonaDB();
			ddb.DBopen();
			int part = Integer.parseInt( ary[0]);
			sql = "insert into Room values(Room_Auto.NEXTVAL, ?, ?, ?)";
			ddb.RunSQL(sql, part, rvo.getROOM_TITLE(),rvo.getUser_NO());
			System.out.println("유저번호232"+rvo.getUser_NO());
			System.out.println("먼저 방 생성 : " + sql);
			
			for(int i = 1; i < ary.length; i++) {
				int part2 = Integer.parseInt( ary[i]);
				sql = "insert into Room values(Room_Auto.CURRVAL, ?, ?, ?)";
				ddb.RunSQL(sql, part2, rvo.getROOM_TITLE(),rvo.getUser_NO());
				System.out.println("방 생성 후 참가자 삽입 : " + sql);
			}
		}
		ddb.DBClose();
		return true;
	}
	public ArrayList<RoomVO> RoomSelect(String no){
		
		ArrayList<RoomVO> list = new ArrayList<RoomVO>();
		ddb = new DalgonaDB();
		ddb.DBopen();
		sql = "select Room_NO, Room_TITLE, Part_NO, USER_NO from Room where USER_NO = ? order by Room_NO";
		
		System.out.println("유저번호"+no);
		ddb.RunQuery(sql, Integer.parseInt(no));
		
		while(ddb.GetNext() == true) 
		{
			RoomVO rvo = new RoomVO();
			rvo.setROOM_NO(ddb.GetString("Room_NO"));
			rvo.setROOM_TITLE(ddb.GetString("Room_TITLE"));
			rvo.setRoom_PARTID(ddb.GetString("Room_PARTID"));
			rvo.setUser_NO(ddb.GetString("USER_NO"));
			list.add(rvo);
		}
		ddb.DBClose();
		return list;
	}
	
}


