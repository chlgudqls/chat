<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시판 글쓰기</h1>

<form name="frm" action="<%=request.getContextPath() %>/board/boardWriteAction.do" method="post">
<input type="text" name="subject">
<input type="submit" name="submit" value="전송">
</form>

</body>
</html>