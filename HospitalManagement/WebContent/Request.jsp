
<%@page import="model.schedule"%>
<%@page import="database.dbconnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    
     <%
if (request.getParameter("si") != null)
{
session.setAttribute("si", request.getParameter("si"));
session.setAttribute("di", request.getParameter("di"));
session.setAttribute("dn", request.getParameter("dn"));
session.setAttribute("d", request.getParameter("d"));
session.setAttribute("r", request.getParameter("r"));

}
    
%>  
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<h1>Doctor Request</h1>
<form method="post" action="Request.jsp" align="center">
SID:        <input name="si" type="text" ><br>
DOC_ID:     <input name="di" type="text" ><br>
DOC_NAME:   <input name="dn" type="text" ><br>
Request Date:<input name="d" type="date" ><br>
Request:     <input name="r" type="text" ><br>
<input name="btnSubmit" type="submit" value="Save"> 
</form>

<br>
<br>
<br>
<br>




<%
 schedule itemObj2 = new schedule();
out.print(itemObj2.readRequest());
%>

<% 

 if (request.getParameter("si") != null)
{
schedule itemObj = new schedule();
String stsMsg = itemObj.insertMessage(
request.getParameter("si"),
request.getParameter("di"),
request.getParameter("dn"),
request.getParameter("d"),
request.getParameter("r"));
session.setAttribute("statusMsg", stsMsg);
}

%>


</body>
</html>