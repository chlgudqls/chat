<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
테스트입니다. 회원아이디 : <%=session.getAttribute("memberId") %>
<a href="<%=request.getContextPath() %>/board/boardWrite.do">글쓰기 페이지</a>
<a href="<%=request.getContextPath() %>/board/boardList.do">게시판 리스트 가기</a>
<a href="<%=request.getContextPath() %>/member/memberList.do">회원 리스트 가기</a>
<a href="<%=request.getContextPath() %>/member/login.do">로그인 하기</a>
</body>
</html>