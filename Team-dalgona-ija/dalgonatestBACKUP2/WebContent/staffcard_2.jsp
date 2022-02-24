<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "DalgoVO.*" %>
<%@ page import = "DalgoDAO.*" %>
<%
	StaffCardDAO sd = new StaffCardDAO();

	MemberVO    svo	= new MemberVO();

	String uno = request.getParameter("uno");
	System.out.println(uno);
	
	svo = sd.StaffCard(uno);
	System.out.println(svo.getPho_No()+ "사진 이름" + svo.getPho_Lo()+ "사진 이름2" +  svo.getPho_Ph());
%>

<!DOCTYPE html>
<html>
<head>

		<meta charset="EUC-KR">
		<title>Insert title here</title>
		<style>
			.first{ 
				writing-mode: vertical-rl;
			}
		</style>
	</head>
	<body  style="padding-left:22%" align=center>
		<table border=1; width=850px; height=900px;>
			<tr>
				<td colspan="6">
					인사 기록 카드
				</td>
			</tr>
			<span style="margin-right:-20%"></span>
			<tr>
				<td class="first" rowspan="2">성 &nbsp;명</td>
				<td>국문</td>
				<td><%= svo.getUSER_KR() %></td>
				<td>성별</td>
				<td style="height:50px;"><%= svo.getUSER_GEN() %></td>
				<td rowspan="3"><img style="width:85%; height:75%;" src="<%=request.getContextPath() + "/image/" + svo.getPho_Ph() %>"></td>
			</tr>
				<tr>
				<td style="height:50px;">한문</td>
				<td><%= svo.getUSER_CH() %></td>
				<td>주민등록번호</td>
				<td><%= svo.getUSER_PNO() %></td>
			</tr>
			<tr>
				<td colspan="2">현주소</td>
				<td colspan="3"><%= svo.getUSER_ADD() %></td>
			</tr>
			<tr>
				<td class="first" style="width:5%" rowspan="2">신&nbsp;체</td>
				<td style="width:19%">신장</td>
				<td style="width:19%">체중</td>
				<td style="width:19%">시력</td>
				<td style="width:19%">혈액형</td>
				<td style="width:19%">색맹</td>
			</tr>
			<tr>
				<td  style="height:40px"></td>
				<td><%= svo.getUSER_CM() %></td>
				<td><%= svo.getUSER_KG() %></td>
				<td><%= svo.getUSER_BT() %></td>
				<td><%= svo.getUSER_COLOR() %></td>
			</tr>
			<tr>
				<td class="first" rowspan="2">&nbsp;최&nbsp;종&nbsp;학&nbsp;력&nbsp;</td>
			</tr>
			<tr	>
				<td style="height:40px" colspan="5"><%= svo.getUSER_FS() %></td>
			</tr>
			<tr>
				<td class="first" style="height:50px" rowspan="2">경&nbsp;력</td>
				<td colspan="5"><%= svo.getUSER_CAA() %></td>
			</tr>
			<tr>
				<td colspan="5"><%= svo.getUSER_CAB() %></td>
			</tr>
			<tr>
				<td class="first" style="height:50px" rowspan="2">자&nbsp;격&nbsp;증</td>
				<td colspan="5"><%= svo.getUSER_LIA() %></td>
			</tr>
			<tr>
				<td colspan="5"><%= svo.getUSER_LIB() %></td>
			</tr>
		</table>
	</body>
</html>