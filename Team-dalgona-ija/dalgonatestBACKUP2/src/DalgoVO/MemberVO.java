package DalgoVO;

import java.io.File;
import java.util.UUID;

public class MemberVO {
	
	private String USER_NO;		//��ȣ
	private String USER_ID;		//���̵�
	private String USER_PW;		//��й�ȣ
	private String USER_CAR;	//������ȣ
	private String USER_KR;		//�̸�(����)
	private String USER_CH;		//�̸�(�ѹ�)
	private String USER_PNO;	//�ֹι�ȣ
	private String USER_GEN;	//���� ���� M, ���� F
	private String USER_ADD;	//�ּ�
	private String USER_CM;		//����
	private String USER_KG;		//ü��
	private String USER_SH;		//�÷�
	private String USER_BT;		//������
	private String USER_COLOR;	//���Ϳ���
	private String USER_FS;		//�����з�
	private String USER_CAA;	//���a
	private String USER_CAB;	//���b
	private String USER_LIA;	//�ڰ���a
	private String USER_LIB;	//�ڰ���b
	private String User_Jd;		//�Ի���
	private String USER_KIND;	//���ѿ��� ���� Y, �̽��� N
	private String USER_TYPE;	//����Ÿ�� ���� O, ���� S
	private String USER_DP;		//�μ� �⺻�� '����'
	private String USER_STATE;  //���� ���� 
	
	//�̹��� ����
	private String Pho_No; 		//�̹��� ��ȣ
	private String Pho_Lo;		//�� �̸�
	private String Pho_Ph;		//���� �̸�
	
	public MemberVO (){
		this.Pho_No = "";
		this.Pho_Lo = "";
		this.Pho_Ph = "";
	}
	
	public String getPho_No() { return Pho_No; }
	public String getPho_Lo() { return Pho_Lo; }
	public String getPho_Ph() { return Pho_Ph; }
	
	public void setPho_No(String pho_No) { Pho_No = pho_No; }
	public void setPho_Lo(String pho_Lo) { Pho_Lo = pho_Lo; }
	public void setPho_Ph(String pho_Ph) { Pho_Ph = pho_Ph; }
	
	public String getUSER_NO()    { return USER_NO;    }
	public String getUSER_ID() 	  { return USER_ID;    }
	public String getUSER_PW() 	  { return USER_PW;    }
	public String getUSER_CAR()   { return USER_CAR;   }
	public String getUSER_KR()	  { return USER_KR;    }
	public String getUSER_CH()	  { return USER_CH;    }
	public String getUSER_PNO()   { return USER_PNO;   }
	public String getUSER_GEN()   { return USER_GEN;   }
	public String getUSER_ADD()   { return USER_ADD;   }
	public String getUSER_CM() 	  { return USER_CM;    }
	public String getUSER_KG() 	  { return USER_KG;    }
	public String getUSER_SH() 	  { return USER_SH;	   }
	public String getUSER_BT() 	  { return USER_BT;	   }
	public String getUSER_COLOR() { return USER_COLOR; }
	public String getUSER_FS() 	  { return USER_FS;    }
	public String getUSER_CAA()	  { return USER_CAA;   }
	public String getUSER_CAB()	  { return USER_CAB;   }
	public String getUSER_LIA()	  { return USER_LIA;   }
	public String getUSER_LIB()	  { return USER_LIB;   }
	public String getUser_Jd() 	  { return User_Jd;    }
	public String getUSER_KIND()  { return USER_KIND;  }
	public String getUSER_TYPE()  { return USER_TYPE;  }
	public String getUSER_DP()    { return USER_DP;    }
	public String getUSER_STATE() { return USER_STATE; }
	
	
	public void setUSER_NO(String uSER_NO) 		 { USER_NO 	  = uSER_NO; 	}
	public void setUSER_ID(String uSER_ID) 		 { USER_ID 	  = uSER_ID; 	}
	public void setUSER_PW(String uSER_PW) 	  	 { USER_PW 	  = uSER_PW; 	}
	public void setUSER_CAR(String uSER_CAR) 	 { USER_CAR   = uSER_CAR; 	}
	public void setUSER_KR(String uSER_KR) 		 { USER_KR 	  = uSER_KR; 	}
	public void setUSER_CH(String uSER_CH) 	 	 { USER_CH 	  = uSER_CH; 	}
	public void setUSER_PNO(String uSER_PNO) 	 { USER_PNO   = uSER_PNO; 	}
	public void setUSER_GEN(String uSER_GEN) 	 { USER_GEN   = uSER_GEN; 	}
	public void setUSER_ADD(String uSER_ADD) 	 { USER_ADD   = uSER_ADD; 	}
	public void setUSER_CM(String uSER_CM) 		 { USER_CM 	  = uSER_CM; 	}
	public void setUSER_KG(String uSER_KG) 		 { USER_KG 	  = uSER_KG; 	}
	public void setUSER_SH(String uSER_SH) 		 { USER_SH 	  = uSER_SH; 	}
	public void setUSER_BT(String uSER_BT) 		 { USER_BT 	  = uSER_BT; 	}
	public void setUSER_COLOR(String uSER_COLOR) { USER_COLOR = uSER_COLOR; }
	public void setUSER_FS(String uSER_FS)		 { USER_FS 	  = uSER_FS; 	}
	public void setUSER_CAA(String uSER_CAA)	 { USER_CAA   = uSER_CAA; 	}
	public void setUSER_CAB(String uSER_CAB)	 { USER_CAB   = uSER_CAB; 	}
	public void setUSER_LIA(String uSER_LIA)	 { USER_LIA	  = uSER_LIA; 	}
	public void setUSER_LIB(String uSER_LIB)	 { USER_LIB	  = uSER_LIB; 	}
	public void setUser_Jd(String user_Jd)		 { User_Jd 	  = user_Jd; 	}
	public void setUSER_KIND(String uSER_KIND)   { USER_KIND  = uSER_KIND; 	}
	public void setUSER_TYPE(String uSER_TYPE)   { USER_TYPE  = uSER_TYPE; 	}
	public void setUSER_DP(String uSER_DP) 		 { USER_DP    = uSER_DP;    }
	public void setUSER_STATE(String uSER_STATE) { USER_STATE = uSER_STATE; }
	
	//÷������
	public void setAttach(String uploadPath,String filename)
	{
		System.out.println(filename);
		
		if(filename == null || filename.equals(""))
		{
			this.Pho_Lo = "";
			this.Pho_Ph = "";			
			return;
		}
		//Ȯ���ڸ� ��´�.
		//String filename = "dog.Jpg";
		String extension = "";				
		int i = filename.lastIndexOf('.');
		if (i > 0) 
		{
			extension = filename.substring(i+1);
		}
		extension = extension.toLowerCase();
		
		String phyname = UUID.randomUUID().toString() + "." + extension;
		String srcName    = uploadPath + "/" + filename;
		String targetName = uploadPath + "/" + phyname;
		File srcFile    = new File(srcName);
		File targetFile = new File(targetName);
		srcFile.renameTo(targetFile);		
		
		this.Pho_Lo = filename;
		this.Pho_Ph = phyname;
	}
}
