package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DalgoDAO.PlanDAO;
import DalgoVO.MemberVO;
import DalgoVO.PlanVO;

@WebServlet("/WorkplanController")
public class PlanController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String 		uri		= request.getRequestURI();
		String 		pj 		= request.getContextPath();
		String 		command = uri.substring(pj.length());
		HttpSession session = request.getSession();
		MemberVO 	uvo 	= (MemberVO) session.getAttribute("login");

		//∞Ë»π ª¿‘
		if(command.equals("/plan/planUpdate.do")) {
			
			String Content   = request.getParameter("Content");
			String StartDate = request.getParameter("StartDate");
			String EndDate   = request.getParameter("EndDate");

			PlanVO  wvo  = new PlanVO();
			PlanDAO wdao = new PlanDAO();
			wvo.setUser_No(uvo.getUSER_NO());
			wvo.setWp_Note(Content);
			wvo.setWp_StartDate(StartDate);
			wvo.setWp_EndDate(EndDate);
			
			wdao.WorkInsert(wvo);			

		}
		else  if (command.equals("/plan/PlanMain.do")) {

			request.setAttribute("flag", 0);
			RequestDispatcher disp = request.getRequestDispatcher("/main_view.jsp?page=1");
			disp.forward(request, response);
			
		
		//∞Ë»π ∞Àªˆ
		}else if(command.equals("/plan/PlanGet.do")) {
			

			PlanDAO 		  wdao = new PlanDAO();
			ArrayList<PlanVO> list = wdao.WorkSearch(uvo.getUSER_NO());
			
			request.setAttribute("list", list);
			request.setAttribute("flag", 1);
			
			RequestDispatcher disp = request.getRequestDispatcher("/main_view.jsp?page=2");
			disp.forward(request, response);
		}	
		//∞Ë»π ºˆ¡§
		else if(command.equals("/plan/PlanModify.do")) {
			String pno       = request.getParameter("no");
			String Content   = request.getParameter("Content");
			String StartDate = request.getParameter("StartDate");
			String EndDate   = request.getParameter("EndDate");
			
			PlanDAO wdao = new PlanDAO();
			PlanVO wvo   = new PlanVO();
			wvo.setWp_No(pno);
			wvo.setWp_Note(Content);
			wvo.setWp_StartDate(StartDate);
			wvo.setWp_EndDate(EndDate);
			wdao.WorkUpdate(wvo);
			
		}
		//∞Ë»π ªË¡¶
		else if(command.equals("/plan/PlanDelete.do")) {
			String  no   = request.getParameter("wno");
			PlanDAO wdao = new PlanDAO();
			PlanVO  wvo  = new PlanVO();
			wdao.WorkDelete(no);
		}
		//∏µÁ ∞Ë»π ªË¡¶
		else if(command.equals("/plan/PlanAllDelete.do")) {

			PlanDAO wdao = new PlanDAO();
			wdao.WorkAllDelete(uvo.getUSER_NO());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
