<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>채팅방 입장</title>
		<script src="jquery-3.6.0.js"></script>
	</head>
	<body>
		<script>
			window.onload = function()
			{
				$("#nick").focus();
			}
			function DoJoin()
			{
				if($("#nick").val() == "")
				{
					alert("닉네임을 입력하세요");
					$("#nick").focus();
					return false;
				}
				return true;
			}
		</script>
		<form id="join" name="join" method="post" onsubmit="return DoJoin();" action="chat.jsp">
			닉네임 : <input type="text" id="nick" name="nick" size="10">
			<input type="submit" value="참여하기">
		</form>
	</body>
</html>
