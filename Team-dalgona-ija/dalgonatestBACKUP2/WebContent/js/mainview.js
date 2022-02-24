var aValue = sessionStorage.getItem("aleady");
$(document).ready(function(){
	if(aValue == null || aValue == 2){
	setTimeout(function() {
		alert('출근 버튼을 누르지 않았습니다')
		clearInterval(interval);		  
		}, 60000);
	}

	
});

function modify()	{ plan(); }
function staffcard(){ window.open('staffcard.jsp', 'stafcard', 'width=1200, height=900, left=1170px, top=80px'); }
function search()   { window.open('search.jsp', 'search', 'width=1000, height=600, left=1170px, top=80px') 		 }

var count = 0; // 취소
var num   = 0;  // 수정하고 추가 막기
//input 추가
function plan_insert() {
	if(num == 1){
		alert("수정중인 텍스트가 존재합니다")
	}
	if(count == 0 && num == 0){
	var tag=
		"<tr id='addnote'><td>N</td><td style='text-align:left'><input type='text' class='Content' name='Content' style='width:90%'></td><td><input style='width:80px' name='StartDate'type='date' ></td><td><input type='date' name='EndDate' style='width:80px'></td><td style='text-align:center'><input type='button' style='background-color:#5FC41F; color:#f0f0f0; width:100%' value='저장' onclick='plan_update()'><td><input type='button' style='background-color:#D0A93B; color:#f0f0f0; width:100%' value='취소' onclick='modify_cancel()'></td></td></td></tr>";
	 $('#my-tbody').append(tag);					 					 	
	}
	count = 1;
 	num = 1;
}
//플랜 변경	 	
//등록 취소		 	
function modify_cancel(){
	var empty = $('.Content').val()
	if(empty == ''){
		$('#addnote').remove();
	}else if(empty != ''){
		var check = confirm('작성중인 텍스트가 있습니다. 취소 하시겠습니까?');
		if(check == true){
			$('#addnote').remove();
		}else{
			$('.Content').focus();
		}
	}
	count = 0;
	num = 0;
}
	 //플랜 삽입
function plan_update(){		
	if($('.Content').val() == "")
	{
		alert("내용을 입력하세요");					 		
		$('.Content').focus();
		return true;
	}else{
		var check = confirm('업데이트 하시겠습니까');
	if(check == true){
		alert('반영되었습니다.');
		document.getElementById('planUp').submit();
		setTimeout(function() {
			location.reload();
			plan();
		}, 100);
	}else{
		alert("취소");
		};	
	}
}
//======================================= 워크플랜 관련 버튼 함수  끝 ================================================

	