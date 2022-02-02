package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDao;
import model.BoardVo;
import model.MemberDao;
import model.MemberVo;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//예시url  /JspTest/board/boardWrite.do
		String uri  = request.getRequestURI();
		String pj = request.getContextPath();
		String command =  uri.substring(pj.length());
	
       if (command.equals("/member/memberList.do")) {	
			
			//처리하는 부분
			MemberDao md = new MemberDao();
			ArrayList<MemberVo> alist = md.selectAllMember();
			
			request.setAttribute("alist", alist);			
			
			RequestDispatcher rd =	request.getRequestDispatcher("/memberList.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/member/login.do")) {
			
			RequestDispatcher rd =	request.getRequestDispatcher("/memberLogin.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/member/loginAction.do")) {
			
			//파라미터로 넘겨받는다.
			String memberId  = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			
			//객체생성후 로그인 메소드를 호출해서 값을 넘긴다.
			MemberDao md = new MemberDao();
			MemberVo mv = md.loginMember(memberId, memberPwd);
			
			//리턴값받은 회원정보값을 세션에 담는다
			HttpSession session = request.getSession();
			session.setAttribute("midx", mv.getMidx());
			session.setAttribute("memberId", mv.getMemberId());
			
			//모든 처리가 끝난후에 이동한다
			response.sendRedirect(request.getContextPath()+"/");
			
		}  
	
	
	
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
