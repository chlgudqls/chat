<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "DalgoUtill.*" %>
<%@ page import = "DalgoVO.*" %>
<%@ page import="java.util.*" %>
<%
//ArrayList<ChatVO>  list = (ArrayList<ChatVO>)request.getAttribute("list"); 
//ChatVO cvo = new ChatVO();
//System.out.println("view " + cvo.getChat_NOTE());
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="<%=request.getContextPath() %>/js/jquery-3.6.0.js"></script>
		<!--  script src="chat.js" charset="utf-8"></script -->
		<script type="text/javascript">
		$(document).ready(function(){
									
			
			var test = $("input[name=msg]").val();
			alert(test);
			
			var msg;
			setInterval(function()
			{
				GetMsg();
			}, 30000);
			
			GetMsg(); //접속하자마자 나옴 */
		  });
		
			
					function send(){								
						if($("#msg").val() == "")
						{
							alert("발송 메시지 입력 바람");
							$("#msg").focus();
						}
						msg = $("#msg").val();
						alert("msg"+msg);
						$.ajax({				
							type : "get",
							url : "<%=request.getContextPath()%>/chat/ChatSend.do",
							dataType : "html",
							data: { msg: msg},
							error:function(xhr,status,e){
			                     alert('Error');
			             	 },
							success : function(data)
							{
								$("#msg").val("");
								$("#msg").focus();
								GetMsg();
							}
						});
					};	
		
		
		function GetMsg()
		{
			//$('#scroll').animate({ scrollTop: $('#scroll')[0].scrollHeight }, 1000);
			var cno = 0;
			//$('#scroll').scrollTop($('#scroll').prop('scrollHeight'));
			
			//var divdiv = document.getElementById("scroll");
			//divdiv.scrollTop = divdiv.scrollHeight;
			
			//const $messageTextBox = $('#scroll');
			//$messageTextBox.scrollTop($messageTextBox[0].scrollHeight);
		



			//alert('메시지 호출')
			$.ajax({	
				type : "get",
				url : "<%=request.getContextPath()%>/chat/ChatGet.do?cno=" + cno,
				dataType : "html",
				success : function(data)
				{
					//alert("받은 메시지" + data);
					//data = data.trim();
					//var aryMsg = data.split("<!--EOR-->");
						$('#getmsg').html(data);
				}
				
				
			});			
		}
		function enterkey() {
		    if (window.event.keyCode == 13) {
		         // 엔터키가 눌렸을 때 실행할 내용
		         send();

		    }
		}
	
		
		</script>
		
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
			<a href="<%=request.getContextPath() %>/chat/chat.do"><span style="margin-left:7px">목록이동</span></a>
			<span style="margin-left:300px">X</span>				
		</div>
		<div id="scroll" class="scroll" style="border:none; width:100%; height: 500px; display:flex; align-items:center; flex-direction:column">							
						
			<div  style="width:100%; height:80px; border:none;">					
				<span id="getmsg" name="getmsg"></span>
			</div>
			<div style="width:352px; margin-right:-1px; margin-top:500px; position:fixed; display:flex;">			
				<div style="width:100%">
					<input type="text" id="msg" name="msg" style="width:100%; height:70px" onkeyup="enterkey();">
				</div>
				<div style="width:65px">
					<input type="button" value="업로드"  style="height:100%;" onclick="send() ">
				</div>		
			</div>	
		</div>	
	</div>
	</body>
</html>
