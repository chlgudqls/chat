<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>    
    
<% ArrayList<BoardVo> alist = (ArrayList<BoardVo>)request.getAttribute("alist");%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시판 리스트</h1>
<table style="width:600px" border="1">
<tr>
<td>번호</td>
<td>제목</td>
</tr>

<% for (BoardVo bv  : alist) { %>
<tr>
<td><%=bv.getBidx() %></td>
<td><a href="<%=request.getContextPath()%>/board/boardContents.do?bidx=<%=bv.getBidx()%>"><%=bv.getSubject() %></a></td>
</tr>

<% } %>

</table>
<table style="width:600px;">
<tr>
<td style="text-align:right;"><a href="<%=request.getContextPath()%>/board/boardWrite.do">게시판 글쓰기</a></td>
</tr>
</table>


</body>
</html>