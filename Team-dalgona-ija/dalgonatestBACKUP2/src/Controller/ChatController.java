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
import DalgoVO.ChatVO;
import DalgoVO.MemberVO;
import DalgoVO.RoomVO;

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
		
		if(command.equals("/chat/ChatSend.do")) {
		
			ChatVO  cvo    = new ChatVO();
			ChatDAO cdao   = new ChatDAO();
			MemberVO  uvo  = new MemberVO();
	      
	      uvo = (MemberVO)session.getAttribute("login");
	      String msg = request.getParameter("msg");
	      System.out.println("msg" + msg);
	      
	      cvo.setUSER_KR(uvo.getUSER_KR());
	      cvo.setChat_NOTE(msg);
	      cvo.setUSER_NO(uvo.getUSER_NO());
	      cdao.ChatInsert(cvo);
		      
		}
		else if(command.equals("/chat/ChatGet.do")) {
			ChatVO cvo   = new ChatVO();
			ChatDAO cdao = new ChatDAO();
			MemberVO  uvo  = new MemberVO();
			String cno   = request.getParameter("cno");
			uvo = (MemberVO)session.getAttribute("login");
		      
		    ArrayList<ChatVO> list = cdao.ChatSelect(uvo.getUSER_NO());
		    request.setAttribute("list", list);
		    list = (ArrayList<ChatVO>) request.getAttribute("list");
		    PrintWriter out = response.getWriter();
		      
		    for(ChatVO cv : list)
		    {
				  
		    	if(uvo.getUSER_NO().equals(cv.getUSER_NO())) {
		    		  
		       	  out.print("<style>div{text-align:right;float:right;width:50%}</style>"
		    	  + "<div>" + uvo.getUSER_KR() + "</div>"
		    	  //+ "<div>" + cv.getChat_DATE() + "</div>"
		    	  + "<div>" + cv.getChat_NOTE() + "</div>");
		    	}else {
	    		  out.print("<style>div{text-align:left;float:left;width:50%}</style>"
	    		    	  + "<div>" + uvo.getUSER_KR() + "</div>"
	    		    	  + "<div>" + cv.getChat_NOTE() + "</div>");
		    	}
		    }   	  
	    }else if(command.equals("/chat/chat.do")) {
	        RoomVO  rvo  = new RoomVO();
	    	RoomDAO rdao = new RoomDAO();
	    	MemberVO  uvo  = (MemberVO)session.getAttribute("login");
	    	
	    	ArrayList<RoomVO> list = rdao.RoomSelect(uvo.getUSER_NO());
	        System.out.println("list8"+list);
	        request.setAttribute("list", list);
	
	        RequestDispatcher disp = request.getRequestDispatcher("/staff/chat.jsp");
			disp.forward(request, response);
			
	    }else if (command.equals("/chat/View.do")) {
	    
	      RequestDispatcher disp = request.getRequestDispatcher("/staff/chatview.jsp");
		  disp.forward(request, response);
	    }
	  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
