package DalgoVO;

public class RoomVO 
{
	private String ROOM_NO;
	private String ROOM_TITLE;
	private String Room_PARTID;
	private String User_NO;
	
	
	
	
	public String getUser_NO() {
		return User_NO;
	}
	public void setUser_NO(String user_NO) {
		User_NO = user_NO;
	}
	public String getROOM_NO() 	  {return ROOM_NO;	  }
	public String getRoom_PARTID(){return Room_PARTID;}
	public String getROOM_TITLE() {return ROOM_TITLE; }
	
	public void setROOM_NO(String rOOM_NO) 			{ROOM_NO = rOOM_NO;	  		}
	public void setRoom_PARTID(String room_PARTID)  {Room_PARTID = room_PARTID; }
	public void setROOM_TITLE(String rOOM_TITLE) 	{ROOM_TITLE = rOOM_TITLE;	}
	
}
