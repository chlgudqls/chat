package DalgoVO;

public class PlanVO {

	private String User_No; 	// �ۼ� ������ȣ
	private String Wp_No;		// ��ȹ ��ȣ
	private String Wp_Note;		// ��������
	private String Wp_StartDate;	// ��ȹ ������
	private String Wp_EndDate;		// ��ȹ ������
	
	public String getUser_No() 				{ return User_No; 	   }
	public String getWp_No()				{ return Wp_No;  	   }
	public String getWp_Note() 				{ return Wp_Note;      }	
	public String getWp_StartDate() 		{ return Wp_StartDate; }	
	public String getWp_EndDate() 			{ return Wp_EndDate;   }
	
	public void setUser_No(String user_No) 			{ User_No	   = user_No;  }
	public void setWp_No(String wp_No)				{ Wp_No  	   = wp_No;    }
	public void setWp_Note(String wp_Note) 			{ Wp_Note      = wp_Note;  }
	public void setWp_StartDate(String wp_Start)	{ Wp_StartDate = wp_Start; }
	public void setWp_EndDate(String wp_End) 		{ Wp_EndDate   = wp_End;   }
		
}
