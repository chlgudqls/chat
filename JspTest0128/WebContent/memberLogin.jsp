<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인 페이지</h1>
<form name="frm" method="post" action="<%=request.getContextPath() %>/member/loginAction.do">
<table border=1 style="width:400px">
<tr><td>id : <input type="text" name="memberId"></td>
<td rowspan=2><input type="submit" name="submit" value="확인"></td>
</tr>
<tr>
<td>pwd : <input type="password" name="memberPwd"></td>
</tr>
</table>
</form>
</body>
</html>