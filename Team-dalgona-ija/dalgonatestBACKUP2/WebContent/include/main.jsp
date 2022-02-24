<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "DalgoUtill.*" %>
<%@ page import = "DalgoVO.*" %>
<%@ page import = "java.util.*" %>
<%
MemberVO uvo = (MemberVO) session.getAttribute("login");
%>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<%=request.getContextPath()%>/js/mainview.js" charset="utf-8"></script>
<script>

	function staff(){ 
		if(aValue == null || aValue == 2){
			alert("출근 해주세요")
		}else{
			location.href = "<%= request.getContextPath()%>/staff/staff.do"
		}
	}
	function plan(){ 
		if(aValue == null || aValue == 2){
			alert("출근 해주세요")
		}else{
			location.href = "<%= request.getContextPath()%>/plan/PlanGet.do"
		}
	}
	function work(){ 
		if(aValue == null || aValue == 2){
		alert("출근 해주세요")
		}else{
			location.href = "<%= request.getContextPath()%>/work/CommuteList.do"
		}
	}
	function main(){ 
		if(aValue == null || aValue == 2){
		alert("출근 해주세요")
		}else{
			location.href = "<%= request.getContextPath()%>/main/main.do"
		}
	}
	
	function card()		{ location.href = "<%= request.getContextPath()%>/staff/staffcard.do"  }
	function plan_modify(Wp_No, Wp_Note, Wp_StartDate, Wp_EndDate, Wp_id, Wp_Start, Wp_End, modi, save, cancle, del){
		if(num == 1){
			alert("수정중인 텍스트가 존재합니다")
		}
		
		if(num == 0){
			num = 1;
			$('#' + Wp_id).html('<input type="text" 	id="note"  class="note" style="width:90%" value="' + Wp_Note + '" >');
			$('#' + Wp_Start).html('<input type="date" 	id="start" style="width:80px" value="' + Wp_StartDate + '" >');
			$('#' + Wp_End).html('<input type="date" 	id="end"   style="width:80px" value="' + Wp_EndDate + '" >');
			
 		$('#' + modi).css('display','none');
 		$('#' + del).css('display','none');
 		
 		$('#' + cancle).css('display','');
 		$('#' + save).css('display','');
 		
 		$('#' + cancle).click(function(){
 			var check = confirm("수정을 취소 하시겠습니까?");
 			if(check == true){
 				location.reload();
	 			plan();
 			}else{
 				$('#note').focus();
 			}
 		})
 		
 		$('#' + save).click(function(){ 
 			var no = Wp_No
 			var Content = $('#note').val()				 			
 			var StartDate = $('#start').val()
 			var EndDate = $('#end').val()

 			if($('.Content').val() == "")
	 		{				 		
 				$('.Content').focus();
 				return true;
 			}else
 			{		 				
		 		if(check != ""){
		 			num = 0;
		 			var check = confirm('업데이트 하시겠습니까');
		 			if(check == true){
			 			location.href = "<%= request.getContextPath()%>/plan/PlanModify.do?no=" + no + "  &Content=  " + Content + "&StartDate=" + StartDate + "&EndDate=" + EndDate;
			 			alert('수정되었습니다.');
			 			location.reload();
			 			plan();
		 			}else{
		 				$('#note').focus();
		 			}
		 		}else{
		 			alert("취소");
	 			};	
 			}

 		})

 	}
	}
	//플랜 삭제
	function plan_delete(Wp_No){										 		 		
		var result = confirm("삭제 하시겠습니까")
		if(result == true){
			location.href = "<%= request.getContextPath()%>/plan/PlanDelete.do?wno=" + Wp_No;
			alert("삭제 되었습니다:>")
			location.reload();
			plan();
		}else{
			alert("삭제 취소!!:>")
		}
	}
	//모든 플랜 삭제
	function plan_DelAll(){
		var result = prompt("모든 데이터를 삭제 하시려면 삭제를 입력하세요");
		if(result == '삭제'){
			location.href = "<%= request.getContextPath()%>/plan/PlanAllDelete.do"
			alert("모든 플랜이 삭제 되었습니다 :>")
			plan();
		}else if (result == null){												
			alert("삭제 취소!! :<")
		}					
		else if(result == '' || result != '삭제'){
			alert("잘못 입력하셨습니다 :<")
		}
	}
	//=================================== (메인)유저 정보 관련 함수 =====================================================================


	function inoffice(uno){
		//var aValue = sessionStorage.getItem("aleady");
			var check = confirm("출근 합니다?")
			if(check == true){
				if(aValue == null || aValue == 2){
					location.href = "<%= request.getContextPath()%>/work/WorkGooffice.do?uno=" + uno;
					alert("정상적으로 출근하였습니다");				
					sessionStorage.setItem("aleady", 1)
				}else if(aValue != null){
					alert("이미 근무중 입니다.")
				}
			}else{
				alert("출근을 취소하였습니다")
		}
	
	}
	
	function outoffice(uno){
		if(aValue != null)
		{
			var check = confirm('퇴근 합니다?')
			if(check == true){
				location.href = "<%= request.getContextPath()%>/work/WorkGohome.do?uno=" + uno;
				sessionStorage.setItem("aleady", 2)
			}
		}else{
			alert("출근을 해주세요");			
		}
	}
	//=========================== 직원 리스트 ====================================
	var num = 0;
	
	function UPDATE_YES(Snum, USER_NO)
	{ 
		var DP 		= $('#Sf_DpV_'   + Snum).val();
		var TYPE	= $('#Sf_TypeV_' + Snum).val();
		var STATE 	= $('#Sf_StateV_'+ Snum).val();

		var result = confirm("수정하시겠습니까?")
	
		if(result == true)
		{
			location.href = "<%= request.getContextPath()%>/staff/staffUpdate.do?DP=" + DP + "&TYPE=" + TYPE + "&STATE=" + STATE + "&USER_NO=" + USER_NO;
			
			$('#Sf_BN_'+Snum).show();
			$('.Sf_BY_'+Snum).hide();
			
			$('#Sf_Dp_'+ Snum).html 	(DP);
			$('#Sf_Type_'+ Snum).html   (TYPE);
			$('#Sf_State_'+ Snum).html 	(STATE);
		}
	}
	
	function Sf_Change(Snum, DP , TYPE, STATE)
	{
		alert(DP);
		
		if( num == 1)
		{
			alert("수정중인 텍스트가 존재합니다")
			
		}
		else if(num == 0)
		{
			//소속부서
			
			if(DP == "인사과")
			{
				$('#Sf_Dp_'+ Snum).html ('<select name="Sf_Dp" id="Sf_DpV_' + Snum + '"> <option value="인사과" id="DP1" selected>인사과</option> <option value="홍보과"  id="DP2">홍보과</option> <option value="기획과"  id="DP3">기획과</option> <option value="행정과"  id="DP4">행정과</option> <option value="--"  id="DP5"> -- </option> </select>');
			}
			else if(DP == "홍보과")
			{
				$('#Sf_Dp_'+ Snum).html ('<select name="Sf_Dp" id="Sf_DpV_' + Snum + '"> <option value="인사과" id="DP1">인사과</option> <option value="홍보과"  id="DP2" selected>홍보과</option> <option value="기획과"  id="DP3">기획과</option> <option value="행정과"  id="DP4">행정과</option> <option value="--"  id="DP5"> -- </option> </select>');				
			}
			else if(DP == "기획과")
			{
				$('#Sf_Dp_'+ Snum).html ('<select name="Sf_Dp" id="Sf_DpV_' + Snum + '"> <option value="인사과" id="DP1">인사과</option> <option value="홍보과"  id="DP2" >홍보과</option> <option value="기획과"  id="DP3" selected>기획과</option> <option value="행정과"  id="DP4">행정과</option> <option value="--"  id="DP5"> -- </option> </select>');								
			}
			else if(DP == "행정과")
			{
				$('#Sf_Dp_'+ Snum).html ('<select name="Sf_Dp" id="Sf_DpV_' + Snum + '"> <option value="인사과" id="DP1">인사과</option> <option value="홍보과"  id="DP2" >홍보과</option> <option value="기획과"  id="DP3" >기획과</option> <option value="행정과"  id="DP4" selected>행정과</option> <option value="--"  id="DP5"> -- </option> </select>');									
			}
			else
			{
				$('#Sf_Dp_'+ Snum).html ('<select name="Sf_Dp" id="Sf_DpV_' + Snum + '"> <option value="인사과" id="DP1">인사과</option> <option value="홍보과"  id="DP2" >홍보과</option> <option value="기획과"  id="DP3" >기획과</option> <option value="행정과"  id="DP4" selected>행정과</option> <option value="--"  id="DP5" selected> -- </option> </select>');													
			}
			
			//직급
			
			if(TYPE == "신입")
			{
				$('#Sf_Type_'+ Snum).html ('<select name="Sf_Type" id="Sf_TypeV_' + Snum + '"> <option value="S" id="Type1">사원</option> <option value="R"  id="Type2" >팀장</option> <option value="O"  id="Type3">사장</option> <option value="I" id="Type4" selected>신입</option> </select>');
			}
			if(TYPE == "팀장")
			{
				$('#Sf_Type_'+ Snum).html ('<select name="Sf_Type" id="Sf_TypeV_' + Snum + '"> <option value="S" id="Type1">사원</option> <option value="R"  id="Type2" selected>팀장</option> <option value="O"  id="Type3">사장</option></select>');
			}
			else if(TYPE == "사장")
			{
				$('#Sf_Type_'+ Snum).html ('<select name="Sf_Type" id="Sf_TypeV_' + Snum + '"> <option value="S" id="Type1">사원</option> <option value="R"  id="Type2">팀장</option> <option value="O"  id="Type3" selected>사장</option></select>');
			}
			else
			{
				$('#Sf_Type_'+ Snum).html ('<select name="Sf_Type" id="Sf_TypeV_' + Snum + '"> <option value="S" id="Type1" selected>사원</option> <option value="R"  id="Type2">팀장</option> <option value="O"  id="Type3">사장</option></select>');
			}
			
			//상태
			if(STATE = "근무중")
			{
				$('#Sf_State_'+ Snum).html ('<select name="Sf_State" id="Sf_StateV_' + Snum + '"> <option value="W" id="State0" selected>근무중</option> <option value="O" id="State5">퇴근</option> <option value="H" id="State1">병가</option> <option value="V"  id="State2">휴가</option> <option value="A"  id="State3">결근</option> <option value="N" id="State4">--</option> </select>');
			}
			else if(STATE = "퇴근")
			{
				$('#Sf_State_'+ Snum).html ('<select name="Sf_State" id="Sf_StateV_' + Snum + '"> <option value="W" id="State0" >근무중</option> <option value="O" id="State5" selected>퇴근</option> <option value="H" id="State1">병가</option> <option value="V"  id="State2">휴가</option> <option value="A"  id="State3">결근</option> <option value="N" id="State4">--</option> </select>');
			}
			else if(STATE = "병가")
			{
				$('#Sf_State_'+ Snum).html ('<select name="Sf_State" id="Sf_StateV_' + Snum + '"> <option value="W" id="State0" >근무중</option> <option value="O" id="State5">퇴근</option> <option value="H" id="State1" selected>병가</option> <option value="V"  id="State2">휴가</option> <option value="A"  id="State3">결근</option> <option value="N" id="State4">--</option> </select>');
			}
			else if(STATE = "휴가")
			{
				$('#Sf_State_'+ Snum).html ('<select name="Sf_State" id="Sf_StateV_' + Snum + '"> <option value="W" id="State0" >근무중</option> <option value="O" id="State5">퇴근</option> <option value="H" id="State1">병가</option> <option value="V"  id="State2" selected>휴가</option> <option value="A"  id="State3">결근</option> <option value="N" id="State4">--</option> </select>');
			}
			else if(STATE = "결근")
			{
				$('#Sf_State_'+ Snum).html ('<select name="Sf_State" id="Sf_StateV_' + Snum + '"> <option value="W" id="State0" >근무중</option> <option value="O" id="State5">퇴근</option> <option value="H" id="State1">병가</option> <option value="V"  id="State2">휴가</option> <option value="A"  id="State3" selected>결근</option> <option value="N" id="State4">--</option> </select>');
			}
			else
			{
				$('#Sf_State_'+ Snum).html ('<select name="Sf_State" id="Sf_StateV_' + Snum + '"> <option value="W" id="State0" >근무중</option> <option value="O" id="State5">퇴근</option> <option value="H" id="State1">병가</option> <option value="V"  id="State2">휴가</option> <option value="A"  id="State3">결근</option> <option value="N" id="State4" selected>--</option> </select>');
			}
			
			$('#Sf_BN_'+Snum).hide();
			
			$('.Sf_BY_'+Snum).show();
			
			num = 1;
		}
	}
	
	function UPDATE_NO(Snum, Dp, Type, State)
	{						

		$('#Sf_BN_'+Snum).show();
		$('.Sf_BY_'+Snum).hide();
		
		$('#Sf_Dp_'+ Snum).html 	(Dp);
		$('#Sf_Type_'+ Snum).html   (Type);
		$('#Sf_State_'+ Snum).html 	(State);
		
		num = 0;
	}
	
	function staffcard_UP(USER_NO){ window.open('<%=request.getContextPath()%>/staffcard_2.jsp?uno='+USER_NO, 'stafcard', 'width=1200, height=900, left=1170px, top=80px'); }

//=========================== 직원 리스트 ====================================
	
//==========================채팅=======================
function chat(){
	if(confirm("채팅 하실건가요") == true){
		
	//	window.open('chat.jsp', 'chat', 'width=370, height=650, left=1170px, top=80px');
		window.open('<%=request.getContextPath()%>/chat/View.do', 'chat', 'width=370, height=650, left=1170px, top=80px');
		}
		
	}
	
</script>