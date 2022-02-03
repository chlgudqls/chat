<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "ezen.*" %>
<%
String msg = request.getParameter("msg");
if(msg == null)return;

String nick = (String)session.getAttribute("nick");

DBManager dbms = new DBManager();
dbms.DBOpen();
String sql = "";
sql = "insert into chat (cname,cnote) ";
sql += "value ('" + nick + "','" + msg + "') ";
dbms.RunSQL(sql);
dbms.DBClose();
%>