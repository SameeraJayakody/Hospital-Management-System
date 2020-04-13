
<%@page import="model.schedule"%>
<%@page import="database.dbconnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Doctor Confirmation</h1>


<%
 schedule itemObj1 = new schedule();
out.print(itemObj1.DisplayDoctor());
%>  












</body>
</html>