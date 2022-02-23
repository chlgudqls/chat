<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "DalgoUtill.*" %>
<%@ page import = "DalgoVO.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script>
		function create(){
			 var chkArray = new Array();
				$('input[name=box]:checked').each(function() { 
					 var tmpVal = $(this).val(); 
				      chkArray.push(tmpVal);
				});
				 if( chkArray.length < 1 ){
				      alert("값을 선택해주시기 바랍니다.");
				      return;
				 }
			var title = prompt("방 제목을 입력해주세요");
			if(title == null){
				alert("방 제목을 입력해주세요")
			}else if(title != null){
				alert("만들었습니다")
				excute(chkArray, title)
			}
		
		}
		function excute(chkArray, title){
	      	location.href = "<%= request.getContextPath()%>/room/generation.do?part=" + chkArray + "&title=" + title
		}
		</script>
		<style>
#scroll{
	overflow:auto;
	display:block; 
	width:100%; 
	height: 100%; 
	border:none;
}
.scroll::-webkit-scrollbar{
	
	width:100px;
	height:100px;
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
	<div>
		<div>
			<div style="display:fixed">
				<input type="button" value="초대" onclick="create()" style="width:100px">
			</div>
			<div style="display:flex;">
				<div id="scroll" class="scroll">
				<%
				ArrayList<MemberVO> list = (ArrayList<MemberVO>) request.getAttribute("list");
				if (list != null) {
				//	for(UserVO uv : list)
					for (int i = 1;i < list.size();i++){
					%>
				<div style="display:flex;">
					<div >
						<input type="checkbox" id = "box" name="box" value="<%=list.get(i).getUSER_NO()%>">
					</div>
					<div style="width:100%">
						<span><%=list.get(i).getUSER_KR()%></span><%//= uv.getUSER_KR() %>
						<span style="font-size:7px"><%=list.get(i).getUSER_DP()%></span><br><%//= uv.getUSER_DP() %>
					</div>
				</div>
					<%
					}
				}
				%>
				</div>	
			</div>	
			</div>		
		</div>
	</body>
</html>