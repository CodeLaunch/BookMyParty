<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.StringTokenizer"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<table>
	<%
		String req = request.getParameter("q");
	
		response.setContentType("text/html");
		response.getWriter().print("<html><body><table>");
		response.getWriter().print("<tr text-align:left><th>NAME<//th><th>PHONE<//th><th>ADDRESS<//th><//tr>");
		StringTokenizer st = new StringTokenizer(req, ",");
		while (st.hasMoreTokens()) {
			//	response.getWriter().print(st.nextElement());
			//response.getWriter().print("</br>");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection con = DriverManager
						.getConnection("jdbc:mysql://localhost/cater_db?"
								+ "user=root&password=r00t");
				Statement sta = con.createStatement();
				ResultSet rs = sta
						.executeQuery("select * from cater_db where name="
								+ '"' + st.nextElement() + '"');
				if (rs.next()) {
	%>
		<%
			response.getWriter().print("<tr valign=\"middle\"><td>" + rs.getString(2)+"</td><td>"+rs.getString(3)+"<//td><//tr>");

					}
				} catch (Exception e) {
					throw e;
				}
		%>
	<%
	response.getWriter().print("<//table><//body><//html>");
	}
	%>
</table>
</body>
</html>