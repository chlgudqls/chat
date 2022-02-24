package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DalgoDAO.MemberDAO;
import DalgoVO.MemberVO;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		int size = 10 * 1024 * 1024;
		String uploadPath = "C:\\Users\\MYCOM\\git\\Team-dalgona\\dalgonatestBACKUP2\\WebContent\\image";
		MultipartRequest multi = 
				new MultipartRequest(request,uploadPath,size,
					"UTF-8",new DefaultFileRenamePolicy());
		
		String uri	    = request.getRequestURI();
		String pj  	    = request.getContextPath();
		String command  = uri.substring(pj.length());
		int    checknum = 0;
		
		if(command.equals("/member/join.do")) {
			
						
			String uSER_ID 	  = multi.getParameter( "User_Id"    );
			String uSER_PW    = multi.getParameter( "User_Pw"    );
			String uSER_CAR   = multi.getParameter( "User_Car"   );
			String uSER_KR 	  = multi.getParameter("User_Kr");
			String uSER_CH 	  = multi.getParameter( "User_Ch"    );
			String uSER_PNO   = multi.getParameter( "User_Pno"   );
			String uSER_GEN   = multi.getParameter( "User_Gen"   );
			String uSER_ADD   = multi.getParameter( "User_Add"   );
			String uSER_CM 	  = multi.getParameter( "User_Cm"	 );
			String uSER_KG	  = multi.getParameter( "User_Kg"	 );
			String uSER_SH 	  = multi.getParameter( "User_Sh"	 );
			String uSER_BT    = multi.getParameter( "User_Bt"	 );
			String uSER_COLOR = multi.getParameter( "User_Color" );
			String uSER_FS    = multi.getParameter( "User_Fs"	 );
			String uSER_CAA   = multi.getParameter( "User_Caa"   );
			String uSER_CAB   = multi.getParameter( "User_Cab"   );
			String uSER_LIA   = multi.getParameter( "User_lia"   );
			String uSER_LIB   = multi.getParameter( "User_lib"   );
			
			
			MemberVO  uvo 	 = new MemberVO();
			MemberDAO udao   = new MemberDAO();
			checknum 	     = udao.UserIdCheck(uSER_ID);
			 if(checknum == 1)
			 {
				 response.sendRedirect(request.getContextPath() + "/join.jsp");
			 }else
			 {
				 uvo.setUSER_ID(uSER_ID);
				 uvo.setUSER_PW(uSER_PW);
				 uvo.setUSER_CAR(uSER_CAR);
				 uvo.setUSER_KR(uSER_KR);
				 uvo.setUSER_CH(uSER_CH);
				 uvo.setUSER_PNO(uSER_PNO);
				 uvo.setUSER_GEN(uSER_GEN);
				 uvo.setUSER_ADD(uSER_ADD);
				 uvo.setUSER_CM(uSER_CM);
				 uvo.setUSER_KG(uSER_KG);
				 uvo.setUSER_SH(uSER_SH);
				 uvo.setUSER_BT(uSER_BT);
				 uvo.setUSER_COLOR(uSER_COLOR);
				 uvo.setUSER_FS(uSER_FS);
				 uvo.setUSER_CAA(uSER_CAA);
				 uvo.setUSER_CAB(uSER_CAB);
				 uvo.setUSER_LIA(uSER_LIA);
				 uvo.setUSER_LIB(uSER_LIB);
				 
				 Enumeration files = multi.getFileNames();
					String fileid 	  = (String) files.nextElement();
					String filename	  = (String) multi.getFilesystemName("Photo");

					if(filename != null)
					{
						uvo.setAttach(uploadPath, filename);
					}
				 udao.UserInsert(uvo);
				 
				 response.sendRedirect(request.getContextPath() + "/login.jsp");
			 }
			 
			 
			 
//			RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
//			disp.forward(request, response);
		
		}else if(command.equals("/member/loginaciton.do")) {
			
			
			String USER_ID = multi.getParameter("USER_ID");
			String USER_PW = multi.getParameter("USER_PW");
			response.setContentType("text/html; charset=euc-kr");
			
			PrintWriter out 	= response.getWriter();
			HttpSession session = request.getSession();
			MemberVO    uvo 	= new MemberVO();
			MemberDAO   udao    = new MemberDAO();
			
			uvo = udao.MemberLogin(USER_ID, USER_PW);
			if(uvo == null)
			{
				out.println("<script>alert('아이디 또는 비밀번호가  틀립니다'); document.location.href = '"+ request.getContextPath() +"/member/login.do' </script>");
				out.flush();
				out.close();
				
			}else
			{
				session.setAttribute("login", uvo);
				   // 세션 유지시간 1시간
			    session.setMaxInactiveInterval(60*60) ;
				uvo = (MemberVO) session.getAttribute("login");
				if((uvo.getUSER_ID().equals(USER_ID) && uvo.getUSER_PW().equals(USER_PW)) == true)
				{
					//페이지 이동
					response.sendRedirect(request.getContextPath() + "/plan/PlanMain.do");	
				}
			}
			//response.sendRedirect(request.getContextPath() + "/main_view.jsp");
			
		}else if(command.equals("/member/login.do"))
		{
			 response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
