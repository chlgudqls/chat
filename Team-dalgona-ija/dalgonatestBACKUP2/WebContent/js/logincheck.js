$(document).ready( function(){
	
	
});
	
	

function FormCheck()
{
	//ID 공란 체크
	if($("#USER_ID").val() == "")
	{
		alert('아이디를 입력해주세요.');
		$("#USER_ID").focus();
		return false;
	}
	//
	if($("#USER_PW").val() == "")
	{
		alert('비밀번호를 입력해주세요.');
		$("#USER_PW").focus();
		return false;
	}
}