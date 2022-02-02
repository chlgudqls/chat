<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>    
    
<% ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 리스트</h1>
<table style="width:600px" border="1">
<tr>
<td>번호</td>
<td>아이디</td>
<td>이름</td>
<td>탈퇴여부</td>
<td>가입일</td>
</tr>

<% for(MemberVo mv : alist) { %>
<tr>
<td><%=mv.getMidx() %></td>
<td><%=mv.getMemberId() %></td>
<td><%=mv.getMemberName() %></td>
<td><%=mv.getDelYn() %></td>
<td><%=mv.getWriteday() %></td>
</tr>
<% } %>

</table>

</body>
</html>