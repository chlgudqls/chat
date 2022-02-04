<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "ezen.*" %>
<%
String msg = request.getParameter("msg");
System.out.println(msg);
if(msg == null)return;

DBManager dbms = new DBManager();
dbms.DBOpen();
String sql = "";
sql = "insert into chat (cnote) ";
sql += "value ('" + msg + "') ";
dbms.RunSQL(sql);
dbms.DBClose();
%>