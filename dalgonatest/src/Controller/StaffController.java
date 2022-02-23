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

import DalgoDAO.PlanDAO;
import DalgoDAO.StaffDAO;
import DalgoVO.MemberVO;

@WebServlet("/StaffController")
public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session   = request.getSession();
		String uri 	   = request.getRequestURI();
		String pj	   = request.getContextPath();
		String command = uri.substring(pj.length());
		MemberVO uvo   = (MemberVO) session.getAttribute("login");
		String USER_POWER = uvo.getUSER_TYPE();
		System.out.println(USER_POWER);
		//계획 삽입
		if(command.equals("/staff/staff.do")) {
			
			StaffDAO sd = new StaffDAO();
			ArrayList<MemberVO> alist = sd.selectStaff();
			
			request.setAttribute("alist", alist);
			request.setAttribute("flag", 3);
			request.setAttribute("USER_POWER", USER_POWER);
			
			RequestDispatcher disp = request.getRequestDispatcher("/main_view.jsp?page=3");
			disp.forward(request, response);
		}else if(command.equals("/staff/staffUpdate.do")) 
		{
			StaffDAO sd = new StaffDAO();
			
			String USER_NO 	= request.getParameter("USER_NO");	//유저넘버
			String DP 	 	= request.getParameter("DP");		//부서
			String TYPE  	= request.getParameter("TYPE");		//직급
			String STATE 	= request.getParameter("STATE");	//상태
			
			System.out.println("유저넘버 : " + USER_NO);
			System.out.println("유저부서 : " + DP);
			System.out.println("유저권한 : " + TYPE);
			System.out.println("유저상태 : " + STATE);
			
			sd.WorkUpdate(USER_NO, DP, TYPE, STATE);

			response.sendRedirect(request.getContextPath() + "/staff/staff.do");
		}else if(command.equals("/staff/staffcard.do")) {
		
			response.sendRedirect(request.getContextPath() + "/staffcard.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
