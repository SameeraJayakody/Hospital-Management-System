
<%@page import="model.schedule"%>
<%@page import="database.dbconnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    
    
    
    
    
    
    
    <%
if (request.getParameter("si") != null)
{
session.setAttribute("si", request.getParameter("si"));
session.setAttribute("stat", request.getParameter("stat"));
}
    
%>  
    
    
    
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>Doctor Confirmation</h1>



<form method="post" action="Doctor.jsp" align="center">
ID:<input name="id" type="text" ><br>
<label for="cars">Status:</label>
  <select id="stat" name="stat">
    <option value="YES">YES</option>
     <option value="NO" selected>NO</option>
    
  </select><br>
  
<!-- Status: <input name="stat" type="text" ><br> -->
<input name="btnSubmit" type="submit" value="Save"> 
</form>


<%
 schedule itemObj1 = new schedule();
out.print(itemObj1.DisplayDoctor());
%>  



<% 
 if (request.getParameter("id") != null)
{
schedule itemObj = new schedule();
String stsMsg = itemObj.updateTes(
request.getParameter("id"),
request.getParameter("stat"));
session.setAttribute("statusMsg", stsMsg);
}


%>








</body>
</html>