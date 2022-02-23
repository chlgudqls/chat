package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String uri	   = request.getRequestURI();
		String pj  	   = request.getContextPath();
		String command = uri.substring(pj.length());
		
		String [] str = command.split("/");
		
		
		if(str[1].equals("member")) {
			MemberController mc = new MemberController();
			mc.doGet(request, response);
		}	
		//플랜페이지 이동
		else if (str[1].equals("plan")) {
			PlanController wk = new PlanController();
			wk.doGet(request, response);			
		}
		//사원리스트 이동	
		else if(str[1].equals("staff")) {
			StaffController sc = new StaffController();
			sc.doGet(request, response);
		}
		//메인페이지 이동
		else if(str[1].equals("main")) {
			MainController mc = new MainController();
			mc.doGet(request, response);
		}	
		//출퇴근 관리 이동
		else if(str[1].equals("work")) {
			WorkController wc= new WorkController();
			wc.doGet(request, response);
			
		}
		//채팅 이동
		else if (str[1].equals("chat")) {
			System.out.println("chat");
			ChatController cc = new ChatController();
			cc.doGet(request, response);			
		}
		//채팅방 이동
		else if(str[1].equals("room")) {
			System.out.println("room");
			RoomController rc = new RoomController();
			rc.doGet(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
