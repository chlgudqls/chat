<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Insert title here</title>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		    function sample6_execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수

		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                //0 == '0' => true
		                //0 === '0' => false(형식도 판별)
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }

		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    // 조합된 참고항목을 해당 필드에 넣는다.
		                    document.getElementById("sample6_extraAddress").value = extraAddr;
		                
		                } else {
		                    document.getElementById("sample6_extraAddress").value = '';
		                }

		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('sample6_postcode').value = data.zonecode;
		                document.getElementById("sample6_address").value = addr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("sample6_detailAddress").focus();
		            }
		        }).open();
		    }
			</script>
	</head>
	<body>
		<style>
			.section input[id*="slide"]{
					display:none;
				}
				.section .slidewrap{
					max-width:1280px;
					margin:0 auto;
					overflow:hidden;
				}
				.section .slidelist{
					white-space:nowrap;	
				}
				.section .slidelist > li{
					display:inline-block;
					vertical-align:middle;
					width:120%;
					transition:all .5s;
				}
				.div{
				}
				.section .slidelist > li > a{
					display:block;
					position:relative;
					left:24%;
				}
				.section .slidelist > li > a > div{
					display:inline-block;
					border:solid 1px;
					width:500px;
					height:900px;
				}
				.section .slidelist > li > a > p{
					width:30px;
					display:inline-block;
					border:solid 1px;
				}
				.section .slidelist > li > a > label
				{
					position:absolute;
					z-index:10;
					top:50%;
					transform:translateY(-50%);
					padding:50px;
					cursor:pointer;			
				}
				.section .slidelist .left{
					left:-270px; 
					background:url('image/left.png')center center / 100% no-repeat;
				}
				.section .slidelist .right{
					right:715px;
					background:url('image/right.png')center center / 100% no-repeat;
				}
				.section [id="slide01"]:checked ~ .slidewrap .slidelist > li {transform:translateX(0.4%);}
				.section [id="slide02"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-100%);}
				.section [id="slide03"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-200.3%);}
				.section .slidelist > li > a >p{
				}
		</style>
		<form name="info" action="#" method="get">
			<div class="section">
			<input type="radio" name="slide" id="slide01" checked>
			<input type="radio" name="slide" id="slide02" >
			<input type="radio" name="slide" id="slide03" >
			<div class="slidewrap" >
				<ul class="slidelist">
					<li>
						<a>
							<div>
								<center>
									<div style="border:solid 1px; height:100px;"><img style="width:50px; margin-top:20px" src="image/close.png"></div>
									<div>
										<input type="text" name="User_Id" placeholder="사용할 아이디" style="width:80%; height:50px; font-size:20px;margin-top:25px">
									</div>
									<div style="margin-right:270px; margin-top:10px">
										<span >아이디 체크</span>
									</div>
									<div>
										<input type="password" name="User_Pw" placeholder="비밀번호" style="width:80%; height:50px; font-size:20px;margin-top:15px">
									</div>
									<div>
										<input type="password" name="User_Pwc" placeholder="비밀번호 확인" style="width:80%; height:50px; font-size:20px;margin-top:25px">
									</div>
									<div style="margin-right:270px; margin-top:10px">
										<span >비밀번호 체크</span>
									</div>
									<div>
										<input type="text" name="User_Car" placeholder="차량번호" style="width:80%; height:50px; font-size:20px;margin-top:25px">
									</div>
								</center>
							</div>
							<label for="slide02" class="right"></label>
						</a>
					</li>
					<li>
						<a>
							<label for="slide01" class="left" ></label>
								<div>
									<center>
										<div>
											<input type="text" name="User_Kr" placeholder="이름(국문)" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="User_Ch" placeholder="이름(한문)" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="User_Pno" placeholder="주민등록번호" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="User_Gen" placeholder="성별" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<div>
												<input type="text" id="sample6_postcode" placeholder="우편번호" style="height:30px; width:60%; margin-top:25px; font-size:20pxfont-size:20px">
												<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" style="width:20%"><br>
											</div>										
											<div>
												<input type="text" name="User_Add" id="sample6_address" placeholder="주소"style="height:30px; width:60%; margin-top:25px; font-size:20pxfont-size:20px"><br>
											</div>
											<div>
												<input type="text" id="sample6_detailAddress" placeholder="상세주소"style="height:30px; width:60%; margin-top:25px; font-size:20pxfont-size:20px">
											</div>
											<div>
												<input type="text" id="sample6_extraAddress" placeholder="참고항목"style="height:30px; width:60%; margin-top:25px; font-size:20pxfont-size:20px">
											</div>
										</div>
										<div>
											<input type="text" name="User_Cm" placeholder="신장" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="User_Kg" placeholder="체중" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="User_Sh" placeholder="시력" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="User_Bo" placeholder="혈액형" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
									</center>
								</div>
							<label for="slide03" class="right"></label>
						</a>
					</li> 
					<li>
						<a>
							<label for="slide02" class="left" ></label>
								<div>
									<center>
										<div>
											<input type="text" name="User_Color" placeholder="색맹" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="" placeholder="사진" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="file" style="font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="User_Fs" placeholder="최종학력" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="User_Ca" placeholder="경력1" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="career2" placeholder="경력2" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="User_li" placeholder="자격증1" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="text" name="license2" placeholder="자격증2" style="width:80%; height:50px; font-size:20px;margin-top:25px">
										</div>
										<div>
											<input type="submit" value="작성완료" style="font-size:20px;background-color:#31B5FF;margin-top:50px; width:350px;height:70px;">
										</div>
									</center>
								</div>
						</a>
					</li> 
				</ul>
			</div>
		</div>
	</form>
	</body>
</html>