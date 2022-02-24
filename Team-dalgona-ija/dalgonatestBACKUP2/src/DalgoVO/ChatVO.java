package DalgoVO;

public class ChatVO {
	
	private String Chat_NO;		//번호
	private String Chat_DATE;	//날짜
	private String Chat_NOTE;	//내용
	private String USER_NO;
	private String ROOM_NO;
	private String Chat_PP;
	private String Chat_HOST;
	private String  USER_KR;
	  
	
	  public String getROOM_NO() 	{return ROOM_NO;		}
	  public String getUSER_KR() 	{return USER_KR;		}
	  public String getChat_PP() 	{return this.Chat_PP;	}
	  public String getChat_HOST()  {return this.Chat_HOST; }
	  public String getChat_NO() 	{return this.Chat_NO;	}
	  public String getChat_NOTE()  {return this.Chat_NOTE; }
	  public String getUSER_NO() 	{return this.USER_NO;	}
	  public String getChat_DATE()  {return this.Chat_DATE;	}
	  
	  public void setROOM_NO(String rOOM_NO) 	 {this.ROOM_NO   = rOOM_NO;	 }
	  public void setUSER_KR(String uSER_KR) 	 {this.USER_KR   = uSER_KR;	 }
	  public void setChat_PP(String chat_PP) 	 {this.Chat_PP   = chat_PP;	 }
	  public void setChat_HOST(String chat_HOST) {this.Chat_HOST = chat_HOST;}
	  public void setChat_NO(String chat_NO) 	 {this.Chat_NO   = chat_NO;	 }
	  public void setChat_NOTE(String chat_NOTE) {this.Chat_NOTE = chat_NOTE;}
	  public void setUSER_NO(String uSER_NO) 	 {this.USER_NO   = uSER_NO;	 }
	  public void setChat_DATE(String chat_DATE) {this.Chat_DATE = chat_DATE;}
}
