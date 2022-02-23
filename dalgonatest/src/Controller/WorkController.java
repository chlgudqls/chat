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

import DalgoDAO.WorkDAO;
import DalgoVO.WorkVO;
import DalgoVO.MemberVO;


@WebServlet("/CommuteController")
public class WorkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charst=UTF-8");
		HttpSession session   = request.getSession();
		String 		uri 	  = request.getRequestURI();
		String 		pj        = request.getContextPath();
		String 		command   = uri.substring(pj.length());
		MemberVO    uvo 	  = (MemberVO) session.getAttribute("login");
		String      USER_TYPE = uvo.getUSER_TYPE();
		int 		USER_NO	  = Integer.parseInt(uvo.getUSER_NO());
		
		//이부분 유저DAO에 User_Kind추가했으니 주의.
				
		//일정보기
		if(command.equals("/work/CommuteList.do")) 
		{
			
			WorkDAO 		  cd 	= new WorkDAO();
			ArrayList<WorkVO> alist = cd.selectCommute(USER_TYPE , USER_NO);

			request.setAttribute("alist", alist);
			request.setAttribute("flag", 4);
						
			RequestDispatcher rd = request.getRequestDispatcher("/main_view.jsp?page=4");
			rd.forward(request, response);
			
		}else if(command.equals("/work/WorkGooffice.do")){			
			String uno   = request.getParameter("uno");
			WorkDAO wdao = new WorkDAO();
			wdao.Staff_Gooffice(uno);
			response.sendRedirect(request.getContextPath() + "/work/CommuteList.do");
			
		}
		else if(command.equals("/work/WorkGohome.do")) {
			String uno = request.getParameter("uno");
			WorkDAO wdao = new WorkDAO();
			
			if(wdao.Staff_Gohome(USER_TYPE, uno) == true)
			{
				session.removeAttribute("login");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
