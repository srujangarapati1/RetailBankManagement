<%@page import="com.bean.Transfer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/designexecutive.css">
<title>Insert title here</title>
 <%@include file="header.jsp" %>
</head>
<body>
<%HttpSession Csession=request.getSession(false); %>
<%if(Csession!=null&&Csession.getAttribute("Role")!=null&&Csession.getAttribute("Role").equals("cashier")){ %>
<ul >
  <li><a href="<%=request.getContextPath()%>/cashierController?action=home">Home</a></li>
  <li><a href="<%=request.getContextPath()%>/cashierController?action=trans">Transfer Amount</a></li>
  <li><a href="<%=request.getContextPath()%>/cashierController?action=statement">Account Statement</a></li>
  <li style="float:Right"><a href="<%=request.getContextPath()%>/cashierController?action=logout">logout</a></li>
     
  
</ul>
<div class="a"><h3 align="right">Welcome Cashier!</h3></div>
<br><br>
<h1 align="center">Amount transfer completed successfully</h1>
<%Transfer t=(Transfer)request.getAttribute("Transfer");
	String SPrev=(String)request.getAttribute("prevSouAmt");
	String SPres=(String)request.getAttribute("preSouAmt");
	String TPrev=(String)request.getAttribute("prevTarAmt");
	String TPres=(String)request.getAttribute("preTarAmt");
%>
<br>
<center>
 <fieldset class="fieldset" align="center">
<h3 align="center">Transferred Balance:<%=t.getAmount() %></h3>

<table border=1>


<tr>
<th>Source ID:</th>
<td><%=t.getSource() %></td>
<th>Target ID:</th>
<td><%=t.getTarget() %></td> 
</tr>
<tr>

<th>Previous Amount:</th><td><%=SPrev %></td> 
<th>Previous Amount:</th><td><%=TPrev %></td> 

</tr>
<tr>

<th>Present Amount:</th><td><%=SPres %></td>
<th>Present Amount:</th><td><%=TPres %></td>
</tr>
</table>


</fieldset>
</center>
<br><br><br>
<%@include file="footer.jsp" %>
</body>

<%} %><%if(Csession==null||Csession.getAttribute("Role")==null||Csession.getAttribute("Role").equals("executive")){ %>
<%response.sendRedirect("login.jsp"); %>
<%} %>
</html>