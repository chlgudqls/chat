<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "ezen.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="jquery-3.6.0.js"></script>
		<script src="chat.js" charset="utf-8"></script>
		<style>
			div{			
				border: 1px solid;
			}
			a{
				text-decoration:none
			}
#scroll{
	overflow:auto;
	display:block; 
	width:100%; 
	height: 100%; 
	border:none;
}
.scroll::-webkit-scrollbar{
	
	width:0px;
	height:0px;
	background-color: #BFCBD1;
	border-radius:10px;
}
.scroll::-webkit-scrollbar-thumb{
	background-color: #35517C;
	border-radius:10px;
}
		</style>
	</head>
	
	<body>
	<div style="width:350px;" >		
		<div style="width:100%;height:50px;">
			<a href="chat.jsp"><span style="margin-left:7px">â</span></a>
			<span style="margin-left:300px">X</span>				
		</div>
		<div id="scroll" class="scroll" style="border:none; width:100%; height: 500px; display:flex; align-items:center; flex-direction:column">					
			<div  style="width:100%; height:80px; border:none;">
				<span id="getmsg" name="getmsg"></span>
			</div>
				
			<div style="width:360px; margin-right:5px; margin-top:500px; position:fixed; display:flex;">			
				<div style="width:100%">
					<input type="text" id="msg" name="msg" style="width:100%; height:70px">
				</div>
				<div>
					<input type="button" value="업로드" id="btnSend" style="height:100%">
				</div>			
			</div>	
			
		</div>	
		
	</div>
	</body>
</html>
</body>
</html>