<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "ezen.*" %>
<%
String no = request.getParameter("cno");//�������� ��ġ�������� no�ι���
if(no == null) no="0"; //no�� ���̸� 0����

DBManager dbms = new DBManager();
dbms.DBOpen();
String sql = "";
//������� �������� �Ϸ��� ���� ���� cno���� ū cno �޽����� ���
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
	
	cnote = cnote.replace("<","&lt;"); //ä�ÿ� HTML�±׾ȸ԰���
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
