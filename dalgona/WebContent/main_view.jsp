<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
			<title>팀장 뷰</title>
			<script src="js/jquery-3.6.0.js"></script>
			<script>
				
			
			 	function staff(){
			 		$('#plan').css('display','none');
			 		$('#mainview').css('display','none');
			 		$('#work').css('display','none');
			 		$('#foot').css('display','none');
			 		$('#staffmanage').css('display','');
			 		$('#staffmanageA').css('display','');
			 	}
			 	function main(){  
			 		$('#plan').css('display','none');
			 		$('#staffmanage').css('display','none');
			 		$('#staffmanageA').css('display','none');
			 		$('#work').css('display','none');
			 		$('#foot').css('display','');
			 		$('#mainview').css('display','');
			 	}
			 	function work(){  
			 		$('#plan').css('display','none');
			 		$('#staffmanage').css('display','none');
			 		$('#staffmanageA').css('display','none');
			 		$('#mainview').css('display','none');
			 		$('#foot').css('display','none');
			 		$('#work').css('display','');
			 	}
			 	function plan(){  
			 		$('#work').css('display','none');
			 		$('#staffmanage').css('display','none');
			 		$('#staffmanageA').css('display','none');
			 		$('#foot').css('display','none');
			 		$('#mainview').css('display','none');
			 		$('#plan').css('display','');
			 	}

				function staffcard(){
					window.open('staffcard.jsp', 'stafcard', 'width=1200, height=900, left=1170px, top=80px')
				}
				
				
				function chat(){
					if(confirm("채팅 하실건가요") == true){
						window.open('chat.jsp', 'chat', 'width=350, height=600, left=1170px, top=80px')
					}
				}
				
				function search(){
					window.open('search.jsp', 'search', 'width=1000, height=600, left=1170px, top=80px')
				}
					
			</script>
		 	<style>
				tr:first-child{
					text-align:center
				}
				td:last-child{
					text-align:center
				}
				#retire{
					text-align:center
				}
				#vacation{
					text-align:center
				}
			</style>
	</head>
	<body>	
		<div style="width:1280px; height:720px; border:solid 1px;">
			<div  style="width:250px; height:720px; display:inline-block; border:solid 1px;">
				<div style="width:250px;display:inline-block;border:solid 1px; height:250px; background-color:#f3f3f3;">
					<div style="display:inline-block;width:90px;">
						<button style="width:100px; height:35px; margin-top:70px; margin-left:20px;color:#f0f0f0; background-color:#244263;" onclick="main();">메인화면</button>
					</div>
					<div style="display:inline-block;width:100px;">
						<button style="width:100px; height:35px; margin-top:70px; margin-left:30px;color:#f0f0f0; background-color:#244263;" onclick="work();">출퇴근관리</button>
					</div>
					<div style="display:inline-block;width:90px;">
						<button style="width:100px; height:35px; margin-top:10px; margin-left:20px;color:#f0f0f0; background-color:#244263;" onclick="plan();">일정표</button>
					</div>
					<div style="display:inline-block;width:100px;">
						<button style="width:100px; height:35px; margin-top:10px; margin-left:30px;color:#f0f0f0; background-color:#244263;" onclick="staff();">사원리스트</button>
					</div>
				</div>	
				<!-- 사용자 정보 -->
				<div style="background-color:#f3f3f3; height:475px;">
					<table border="1">						
						<tr >
							<td style="color:#46709C; font-weight:bold">사원 정보</td>
							<td>
								<select>
									<option>휴가</option>
									<option>근무중</option>
									<option>병가</option>
									<option>외근</option>
									<option>부재중</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:135px; height:154px;">
							</td>
							<td>
								<input type="button"style="background-color:#555237;color:#f0f0f0" value="출근"> <br>
								<input type="button"style="background-color:#555237;color:#f0f0f0" value="퇴근">
							</td>
						</tr>
						<tr>
							<td style="color:#46709C;">이름</td>
							<td>이름입니다.</td>
						</tr>
						<tr>
							<td style="color:#46709C;">구분</td>
							<td>구분입니다.</td>
						</tr>
						<tr>
							<td style="color:#46709C;">부서</td>
							<td>부서입니다.</td>
						</tr>
						<tr>
							<td style="color:#46709C;">직위</td>
							<td>직위입니다.</td>
						</tr>
						<tr>
							<td style="color:#46709C;">번호</td>
							<td>번호입니다.</td>
						</tr>
						<tr>
							<td id="staff" colspan="2">
								<input style="color:#f0f0f0; background-color:#1F5A6F;" type="button" value="인사카드" onclick="staffcard()">
							</td>
						</tr>
					</table>										
				</div>
				<!-- 사용자 정보 -->
			</div>
				<div  style="width:1000px; height:720px; display:inline-block; float:right; background-color:; border:solid 1px;">
					<table style=" display:none; width:100%; height:50px" id="staffmanageA">
						<tr>
							<td style="text-align:left">
								<select>
									<option>부서정보</option>
								</select>
							</td>
							<td style="text-align:right">
								<input type="button"style="background-color:#49395F;color:#f0f0f0" value="수정">
								<input type="button"style="background-color:#49395F;color:#f0f0f0" value="삭제">
							</td>
						</tr>
					</table>
					<!----------------------- 사원리스트 태그 ------------------------->
					<table border="1" style="width:100%; display:none" id="staffmanage">
						<tr style="font-weight:bold;background-color:#DBE4F1;">
							<td>
								부서
							</td>
							<td>
								직급
							</td>
							<td>
								이름
							</td>
							<td>
								번호
							</td>
							<td>
								직원상태
							</td>
							<td>
								채팅
							</td>
							<td>
								인사카드
							</td>
						</tr>
						<tr>
							<td>
								법무부
							</td>
							<td>
								사원
							</td>
							<td>
								홍길동
							</td>
							<td>
								011-8465-4652
							</td>
							<td>
								근무중
							</td>

							<td style="text-align:center">
								<input type="button" style="background-color:#71292D;color:#f0f0f0;" value="채팅" onclick="chat()">
							</td>
							<td id="staff">
								<input style="color:#f0f0f0; background-color:#1F5A6F;" type="button" value="인사카드" onclick="staffcard()">
							</td>
						</tr>
						<tr>
							<td>
								법무부
							</td>
							<!-- 인증시 출력 -->
							<td>
								사원
							</td>
							<!-- 인증시 출력 -->
							
							<!-- 미인증시 출력 -->
							<td colspan="2" style="display:none">
								<button>코드발급</button>
							</td>
							<!-- 미인증시 출력 -->
							
							<td>
								최형빈
							</td>
							<td>
								011-8465-4652
							</td>
							<td>
								자택근무
							</td>							
							<td style="text-align:center">
								<input type="button"style="background-color:#71292D;color:#f0f0f0;" value="채팅" onclick="chat()">
							</td>
							<td id="staff">
								<input style="color:#f0f0f0; background-color:#1F5A6F;" type="button" value="인사카드" onclick="staffcard()">
							</td>
						</tr>
					</table>
					<!----------------------- 사원리스트 태그 ------------------------->
					
					<!----------------------- 출퇴근 태그 ------------------------->
					<table border="1" style="width:100%;display:none"; id="work">
						<tr style="font-weight:bold;background-color:#DBE4F1; ">
							<td>
								이름
							</td>
							<td>
								월 / 일
							</td>
							<td>
								출근 시간
							</td>
							
							<td>
								퇴근시간
							</td>
							<td>
								비고
							</td>
						</tr>
						<tr>
							<td>
								홍길동
							</td>
							<td>
								2022-01-28
							</td>
							<td>
								08:57
							</td>
							<td>
								18:14
							</td>		
																
							<td>
								칼퇴근
							</td>
						</tr>
					
					</table>
					<!----------------------- 출퇴근 태그 ------------------------->
					
					<!----------------------- 일정표 태그 ------------------------->
					<table border="1" style="width:100%; display:none" id="plan">
						<tr>
							<td style="background-color:#DBE4F1;font-weight:bold">
								스케줄 관리 페이지
							</td>
						</tr>
					</table>
					<!----------------------- 일정표 태그 ------------------------->
				
				<!-- 일정표 -->
				<div id="foot">
					<div style="width:600px; height:400px; margin-top:25px; margin-left:45px; background-color:#f3f3f3; display:inline-block;">일정표 입니다.</div>
					<div style="width:300px; height:400px; margin-top:25px; margin-right:45px; background-color:#f6f6f6; display:inline-block; float:right; ">일정표 리스트 입니다.</div>
				</div>
				<!-- 일정표 -->
			</div>				
			</div>
	</body>
</html>