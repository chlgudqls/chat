package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DalgoDAO.RoomDAO;
import DalgoVO.ChatVO;
import DalgoVO.RoomVO;
import DalgoVO.MemberVO;

@WebServlet("/RoomController")
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
	    String uri = request.getRequestURI();
	    String pj = request.getContextPath();
	    String command = uri.substring(pj.length());
	    HttpSession session = request.getSession();
	    
	    if(command.equals("/room/generation.do")) {
	    	RoomVO rvo = new RoomVO();
	    	RoomDAO rdao = new RoomDAO();
	    	MemberVO  uvo  = new MemberVO();
	    	uvo = (MemberVO)session.getAttribute("login");
	    	
	    	String part = request.getParameter("part");
	    	String title = request.getParameter("title");
	    	
	    	rvo.setRoom_PARTID(part);
	    	rvo.setROOM_TITLE(title);
	    	rvo.setUser_NO(uvo.getUSER_NO());
	    	rdao.createRoom(rvo);
	    	
	    	response.sendRedirect(request.getContextPath()+"/chat/chat.do");
	    	
	    	//RequestDispatcher disp = request.getRequestDispatcher("/staff/chat.jsp");
			//disp.forward(request, response);
			System.out.println("11");
	    	
	    }else if(command.equals("/room/stafflist.do")) {
	    	System.out.println("/room/stafflist.do");
	    	//RoomVO rvo = new RoomVO();
	    	RoomDAO rdao = new RoomDAO();
	    	MemberVO uvo   = null;
	    	
	   // 	uvo = (UserVO)session.getAttribute("login");
	 //   	int uno = Integer.parseInt(uvo.getUSER_NO());
	        ArrayList<MemberVO> list = rdao.StaffList();
	        System.out.println("list2"+list);
	        request.setAttribute("list", list);
	                
	        
	        //list = (ArrayList<RoomVO>) request.getAttribute("list");
	        RequestDispatcher disp = request.getRequestDispatcher("/staff/invite.jsp");
			disp.forward(request, response);
			System.out.println("22");
	    }
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
