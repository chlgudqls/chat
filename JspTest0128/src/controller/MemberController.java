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
		
		//����url  /JspTest/board/boardWrite.do
		String uri  = request.getRequestURI();
		String pj = request.getContextPath();
		String command =  uri.substring(pj.length());
	
       if (command.equals("/member/memberList.do")) {	
			
			//ó���ϴ� �κ�
			MemberDao md = new MemberDao();
			ArrayList<MemberVo> alist = md.selectAllMember();
			
			request.setAttribute("alist", alist);			
			
			RequestDispatcher rd =	request.getRequestDispatcher("/memberList.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/member/login.do")) {
			
			RequestDispatcher rd =	request.getRequestDispatcher("/memberLogin.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/member/loginAction.do")) {
			
			//�Ķ���ͷ� �Ѱܹ޴´�.
			String memberId  = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			
			//��ü������ �α��� �޼ҵ带 ȣ���ؼ� ���� �ѱ��.
			MemberDao md = new MemberDao();
			MemberVo mv = md.loginMember(memberId, memberPwd);
			
			//���ϰ����� ȸ���������� ���ǿ� ��´�
			HttpSession session = request.getSession();
			session.setAttribute("midx", mv.getMidx());
			session.setAttribute("memberId", mv.getMemberId());
			
			//��� ó���� �����Ŀ� �̵��Ѵ�
			response.sendRedirect(request.getContextPath()+"/");
			
		}  
	
	
	
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
