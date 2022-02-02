<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String subject = (String)request.getAttribute("subject");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>내용보기</h1>
<table style="width:600px;height:400px" border=1>
<tr>
<td>제목</td>
<td><%=subject %></td>
</tr>
<tr style="height:50px;">
<td colspan=2 style="text-align:right;">수정  삭제   목록</td>
</tr>

</table>
</body>
</html>