<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "ezen.*" %>
<%
String no = request.getParameter("cno");//변수명이 겹치지때문에 no로받음
if(no == null) no="0"; //no가 널이면 0으로

DBManager dbms = new DBManager();
dbms.DBOpen();
String sql = "";
//순서대로 오름차순 하려면 새로 받은 cno보다 큰 cno 메시지만 출력
sql = "select cno,cdate,cnote ";
sql += "from chat ";
sql += "where cno > " + no + " ";
sql += "order by cno asc ";
dbms.OpenQuery(sql);

while(dbms.GetNext() == true)
{
	String cno = dbms.GetValue("cno");
	String cdate = dbms.GetValue("cdate");
	String cnote = dbms.GetValue("cnote");
	
	cnote = cnote.replace("<","&lt;"); //채팅에 HTML태그안먹게함
	cnote = cnote.replace(">","&gt;");
	
	//out.print("[" + cdate + "]" + cnote + "<br>");
	out.print(cno);
	out.print("<!--EOF-->");
	out.print("[" + cdate + "]" + cnote + "<br>");
	out.print("<!--EOR-->");
}
dbms.CloseQuery();
dbms.DBClose();
%>
