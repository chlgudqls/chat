package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DalgoDAO.ChatDAO;
import DalgoDAO.MemberDAO;
import DalgoDAO.RoomDAO;
import DalgoVO.*;


/**
 * Servlet implementation class MemberController
 */
@WebServlet("/ChatController")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uri	   = request.getRequestURI();
		String pj  	   = request.getContextPath();
		String command = uri.substring(pj.length());
		
		HttpSession session = request.getSession();
		
		if (command.equals("/chat/ChatSend.do")) {
		      ChatVO  cvo  = new ChatVO();
		      ChatDAO cdao = new ChatDAO();
		      MemberVO  mvo  = new MemberVO();
		      
		      mvo = (MemberVO)session.getAttribute("login");
		      String msg = request.getParameter("msg");
		      System.out.println("msg" + msg);
		      
		      cvo.setUSER_KR(mvo.getUSER_KR());
		      cvo.setChat_NOTE(msg);
		      cvo.setUSER_NO(mvo.getUSER_NO());
		      cdao.ChatInsert(cvo);

		    }
		    else if (command.equals("/chat/ChatGet.do")) {
		      System.out.println("메시지를 호출합니다22");
		      ChatVO cvo   = new ChatVO();
		      ChatDAO cdao = new ChatDAO();
		      MemberVO mvo   = new MemberVO();
		      String ano = request.getParameter("ano");
		      String uno = request.getParameter("uno");
		      
		      System.out.println("내 번호 제발 되어라" + uno);
		      System.out.println("상대방 번호 제발 되어라" + ano);
		      String cno   = request.getParameter("cno");
		      mvo = (MemberVO)session.getAttribute("login");
		      
		      ArrayList<ChatVO> list = cdao.ChatSelect2(ano, uno);
		      request.setAttribute("list", list);
		      //System.out.println("controller : " + cvo.getChat_NOTE());
		      list = (ArrayList<ChatVO>) request.getAttribute("list");
		      PrintWriter out = response.getWriter();
		      
		      for(ChatVO cv : list)
		      {
		    	  System.out.println("현재 로그인한 번호" + cv.getUSER_NO());
				  System.out.println("메시지 주인 번호" + cv.getUSER_NO());
				  
		    	  if(mvo.getUSER_NO().equals(cv.getUSER_NO())) {
		    		  
			       	  out.print("<style>div{text-align:right;float:right;width:50%}</style>"
			    	  + "<div>" + mvo.getUSER_KR() + "</div>"
			    	  //+ "<div>" + cv.getChat_DATE() + "</div>"
			    	  + "<div>" + cv.getChat_NOTE() + "</div>");
		    	  }else {
		    		  out.print("<style>div{text-align:left;float:left;width:50%}</style>"
		    		    	  + "<div>" + mvo.getUSER_KR() + "</div>"
		    		    	  + "<div>" + cv.getChat_NOTE() + "</div>");
		    	  }
		      }     
		    
	    }else if(command.equals("/chat/chat.do")) {
	        RoomVO  rvo  = new RoomVO();
	    	RoomDAO rdao = new RoomDAO();
	    	MemberVO  mvo  = (MemberVO)session.getAttribute("login");
	    	
	    	ArrayList<RoomVO> list = rdao.RoomSelect(mvo.getUSER_NO());
	        System.out.println("list8"+list);
	        request.setAttribute("list", list);
	
	        RequestDispatcher disp = request.getRequestDispatcher("/staff/chat.jsp");
			disp.forward(request, response);
			
	    }else if (command.equals("/chat/View.do")) {
	    	System.out.println("메시지를 호출합니다");
	    	MemberVO  uvo  = (MemberVO)session.getAttribute("login");
	    	
	    	RoomVO rvo = new RoomVO();
	    	ChatDAO rdao = new ChatDAO();
	    	String ano = request.getParameter("ano");
	    	String uno = request.getParameter("uno");
	    	rdao.ChatSelect(uno, ano);
	    	System.out.println("페이지 이동");
	    	RequestDispatcher disp = request.getRequestDispatcher("/staff/chatview.jsp");
			disp.forward(request, response);
	    	
	    }else if(command.equals("/chat/create.do")) {
	    	MemberVO  uvo  = (MemberVO)session.getAttribute("login");
	    	ChatDAO rdao = new ChatDAO();
	    	RoomVO rvo = new RoomVO();
	    	String ano = request.getParameter("ano");
	    	String title = request.getParameter("title");
	    	rvo.setUser_NO(uvo.getUSER_NO());
	    	String uno = rvo.getUser_NO();
	    	rvo.setROOM_TITLE(title);
	    	
	    	rdao.ChatCreate(ano, uno, rvo.getROOM_TITLE());
	    	response.sendRedirect(request.getContextPath()+"/chat/View.do?ano=" + ano + "&uno=" + uno);
	    	//rdao.ChatSelect(rvo.getUser_NO(), ano);
	    	
	    }
	  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
