
<%@page import="model.schedule"%>
<%@page import="database.dbconnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    
    
    
       <%
if (request.getParameter("si") != null)
{
session.setAttribute("si", request.getParameter("si"));
session.setAttribute("hi", request.getParameter("hi"));
session.setAttribute("hn", request.getParameter("hn"));
session.setAttribute("di", request.getParameter("di"));
session.setAttribute("dn", request.getParameter("dn"));
session.setAttribute("sp", request.getParameter("sp"));
session.setAttribute("d", request.getParameter("d"));
session.setAttribute("s", request.getParameter("s"));
session.setAttribute("e", request.getParameter("e"));
session.setAttribute("r", request.getParameter("r"));
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




<h1>Schedule Management</h1>
<form method="post" action="TimeCollector.jsp" align="center">
SID:        <input name="si" type="text" ><br>
HOS_ID:     <input name="hi" type="text" ><br>
HOS_NAME:   <input name="hn" type="text" ><br>
DOC_ID:     <input name="di" type="text" ><br>
DOC_NAME:   <input name="dn" type="text" ><br>
SPECIALITY: <input name="sp" type="text" ><br>
DATE:       <input name="d" type="date" ><br>
START:      <input name="s" type="time" ><br>
END:        <input name="e" type=time ><br>
ROOM:       <input name="r" type="text" ><br>
<label for="cars">Status:</label>
  <select id="stat" name="stat">
    <option value="YES">YES</option>
     <option value="NO" selected>NO</option>
    
  </select><br>
  
<!-- Status: <input name="stat" type="text" ><br> -->
<input name="btnSubmit" type="submit" value="Save"> 
</form>



<br>
<br>
<br>
<br>

 
 <table border="1" align="center">
<tr>
<th>SID</th>
<th>HOS_ID</th>
<th>HOS_NAME</th>
<th>DOC_ID</th>
<th>DOCNAME</th>
<th>SPECIALITY</th>
<th>DATE</th>
<th>START</th>
<th>END</th>
<th>ROOM</th>
<th>STATUS</th>
<th>update</th>
<th>remove</th>
</tr>
<tr>
<td><%out.print(session.getAttribute("si")); %></td>
<td><%out.print(session.getAttribute("hi")); %></td>
<td><%out.print(session.getAttribute("hn")); %></td>
<td><%out.print(session.getAttribute("di")); %></td>
<td><%out.print(session.getAttribute("dn")); %></td>
<td><%out.print(session.getAttribute("sp")); %></td>
<td><%out.print(session.getAttribute("d")); %></td>
<td><%out.print(session.getAttribute("s")); %></td>
<td><%out.print(session.getAttribute("e")); %></td>
<td><%out.print(session.getAttribute("r")); %></td>
<td><%out.print(session.getAttribute("stat")); %></td>
<td><input name="btnUpdate" type="button" value="Update"></td>
<td><input name="btnRemove" type="button" value="Remove"></td>
</tr>
</table>  
<br>
<br>
<br>
<br>
<br>
 



 <%  

    
    if (request.getParameter("sid") != null)
    {
    dbconnect itemObj = new dbconnect();
    itemObj.connect();//For testing the connect method
    }


 %>


 
  <% 
  
 
 if (request.getParameter("si") != null)
{
schedule itemObj = new schedule();
String stsMsg = itemObj.insertItem(
request.getParameter("si"),
request.getParameter("hi"),
request.getParameter("hn"),
request.getParameter("di"),
request.getParameter("dn"),
request.getParameter("sp"),
request.getParameter("d"),
request.getParameter("s"),
request.getParameter("e"),
request.getParameter("r"),
request.getParameter("stat"));
session.setAttribute("statusMsg", stsMsg);
}

 // response.sendRedirect("Doctor.jsp");
%> 
 

 <%
 schedule itemObj1 = new schedule();
out.print(itemObj1.readItems());
%>  


<% 
if (request.getParameter("id") != null)
{
	schedule itemObj = new schedule();
String stsMsg = itemObj.deleteItem(request.getParameter("id"));
session.setAttribute("statusMsg", stsMsg);
}

%> 


<%-- <% 
 if (request.getParameter("si") != null)
{
Tabj itemObj = new Tabj();
String stsMsg = itemObj.updateItem(request.getParameter("id"),
request.getParameter("si"),
request.getParameter("hi"),
request.getParameter("hn"),
request.getParameter("di"),
request.getParameter("dn"),
request.getParameter("sp"),
request.getParameter("d"),
request.getParameter("s"),
request.getParameter("e"),
request.getParameter("r"));
session.setAttribute("statusMsg", stsMsg);
}


%> --%>


 


</body>
</html>