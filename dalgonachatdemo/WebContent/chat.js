
window.onload = function()
{
	//alert("ok");
	
	$("#btnSend").click(function()
	{
		//alert("click!!!");
		//var org_html = $("#chatMsg").html();
		//org_html = "[날짜]안녕하세요?<br>" + org_html;
		//$("#chatMsg").html(org_html);
		SendMsg();
	});
	setInterval(function()
	{
		GetMsg();
	}, 1000);
	GetMsg(); //접속하자마자 나옴
}

function SendMsg()
{
	
	if($("#msg").val() == "")
	{
		alert("발송 메시지 입력 바람");
		$("#msg").focus();
		return false; //false==온서브밋막음 엔터로 넘어가지않게하려고 
	}
	var postData = "";
	postData = "msg=" + $("#msg").val();
	
	$.ajax({
		type : "post",
		url : "sendmsg.jsp",
		data : postData,
		dataType : "html",
		success : function(data)
		{
			//alert(data);
			$("#msg").val("");
			$("#msg").focus();
			
			GetMsg();
		}
	});
	return false;
	
}

var cno ="0";
function GetMsg()
{
	$.ajax({
		type : "get",
		url : "getmsg.jsp?cno=" + cno,//0
		dataType : "html",
		success : function(data)
		{
			/*
			var org_html = $("#chatMsg").html();
			org_html = data + org_html;
			$("#chatMsg").html(org_html);
			*/
			//alert(data);
			data = data.trim();
			var aryMsg = data.split("<!--EOR-->");
			for(i=0;i<aryMsg.length;i++)
			{
				var item = aryMsg[i].split("<!--EOF-->");
				if(item[0] != "")
				{
					cno = item[0];
					var org_html = $("#getmsg").html();
					org_html = item[1] + org_html;
					$("#getmsg").html(org_html);
				}
			}
		}
	});
}
