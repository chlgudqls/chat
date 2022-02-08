<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			div{			
				border: 1px solid;
			}
			a{
				text-decoration:none
			}
		</style>
	</head>
<body>
	<div style="width:350px; height:600px;">		
		<div style="width:100%;height:50px;">
			<a href="chat.jsp"><span style="margin-left:7px">◀</span></a>
			<span style="margin-left:300px">X</span>				
		</div>
		<div style=" width:100%; height: 100%; display:flex; align-items:center; flex-direction:column">		
		
			
			<div style="width:100%; height:80px;margin-top:20px; display:flex">
				<div style="width:30%; height:100%">
					<span>이미지</span>
				</div>	
				<div style="margin-left:20px; width:90%">
					<span>안녕하세요~~~</span>
				</div>
			</div>
			<div style="width:100%; height:80px;margin-top:20px; display:flex">
			<div style="width:90%">
					<span>안녕하세요~~~</span>
				</div>
				<div style="width:30%; height:100%; margin-left:20px">
					<span>이미지</span>
				</div>					
			</div>

			<div style="width:350px; margin-right:5px;margin-top:75%; display:flex;">
				<div style="width:100%">
					<input type="text" style="width:100%; height:70px">
				</div>
				<div>
					<input type="button" value="업로드" style="height:100%">
				</div>
			</div>			
		</div>	
	</div>
	</body>
</html>