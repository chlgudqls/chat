package DalgoVO;

public class WorkVO 
{
	//조회용
	private int User_No;		//유저번호
	private String Co_No;       //출근 번호
	private String Co_Md;		//출근날짜
	private String Co_Start;	//출근시간
	private String Co_End;		//퇴근시간
	private String Co_note;		//비고
	private String User_kr;		//이름
	private String Co_Ci;		//출근여부
	
	
	
	public int    getUser_No() 	{ return User_No;  }
	public String getCo_Md()	{ return Co_Md;	   }
	public String getCo_Start()	{ return Co_Start; }
	public String getCo_End() 	{ return Co_End;   }
	public String getCo_note() 	{ return Co_note;  }
	public String getUser_kr() 	{ return User_kr;  }
	public String getCo_Ci() 	{ return Co_Ci;	   }
	public String getCo_No() 	{ return Co_No;	   }
	
	public void setUser_No(int user_No) 		{ User_No  = user_No;  }
	public void setCo_Md(String co_Md) 			{ Co_Md    = co_Md;    }
	public void setCo_Start(String co_Start) 	{ Co_Start = co_Start; }
	public void setCo_End(String co_End) 		{ Co_End   = co_End;   }
	public void setCo_note(String co_note) 		{ Co_note  = co_note;  }
	public void setUser_kr(String user_kr) 		{ User_kr  = user_kr;  }
	public void setCo_Ci(String co_Ci) 			{ Co_Ci    = co_Ci;    }
	public void setCo_No(String co_No) 			{ Co_No    = co_No;    }
//	//검색용
//	private String c_year;		//년
//	private String c_month;		//월
//
//	public String getC_year()  {return c_year;}
//	public String getC_month() {return c_month;}
//
//	public void setC_year(String c_year) 	{this.c_year = c_year;	}
//	public void setC_month(String c_month)  {this.c_month = c_month;}
	
	
}
