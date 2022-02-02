package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDao;
import model.BoardVo;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//예시url  /JspTest/board/boardWrite.do
		String uri  = request.getRequestURI();
		String pj = request.getContextPath();
		String command =  uri.substring(pj.length());
		
		//System.out.println("추출주소값"+command);
		
		if (command.equals("/board/boardWrite.do")) {
			//글쓰기 페이지로 이동한다			
		RequestDispatcher rd = request.getRequestDispatcher("/boardWrite.jsp");			
		rd.forward(request, response);	
		
		}else if (command.equals("/board/boardWriteAction.do")) {
			
			String subject = request.getParameter("subject");

			//2. 넘어온 값을 DB에 입력한다 (처리)
			BoardDao  db =  new BoardDao();
			int exec = db.insertBoard(subject);		
		
			if (exec !=0) {
			response.sendRedirect(request.getContextPath()+"/index.jsp");	
			}else 
			response.sendRedirect(request.getContextPath()+"/board/boardWrite.do");	
		
		}else if (command.equals("/board/boardList.do")) {	
			
			//처리하는 부분
			BoardDao bd = new BoardDao();
			ArrayList<BoardVo> alist = bd.selectAllBoard();
			
			request.setAttribute("alist", alist);			
			
			RequestDispatcher rd =	request.getRequestDispatcher("/boardList.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardContents.do")) {
			
			String bidx = request.getParameter("bidx");
			int bidx2 = Integer.parseInt(bidx);
			
			BoardDao bd = new BoardDao();
			String subject= bd.selectOneBoard(bidx2);			
			
			request.setAttribute("subject", subject);
			
			RequestDispatcher rd =	request.getRequestDispatcher("/boardContents.jsp");
			rd.forward(request, response);
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
