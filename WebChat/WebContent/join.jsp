<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>ä�ù� ����</title>
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
					alert("�г����� �Է��ϼ���");
					$("#nick").focus();
					return false;
				}
				return true;
			}
		</script>
		<form id="join" name="join" method="post" onsubmit="return DoJoin();" action="chat.jsp">
			�г��� : <input type="text" id="nick" name="nick" size="10">
			<input type="submit" value="�����ϱ�">
		</form>
	</body>
</html>
