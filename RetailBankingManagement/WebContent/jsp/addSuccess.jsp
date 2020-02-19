<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/designexecutive.css">
<title>Insert title here</title>

</head>
<body>
<%HttpSession Esession=request.getSession(false); %>
<%if(Esession.getAttribute("Role")!=null&&Esession.getAttribute("Role").equals("executive")){ %>


<ul >
  <li><a href="<%=request.getContextPath()%>/executiveController?action=home">Home</a></li>
  <li><a href="<%=request.getContextPath()%>/executiveController?action=customer">Add Customer</a></li>
  <li><a href="<%=request.getContextPath()%>/executiveController?action=account">Add Account</a></li>
  <li style="float:Right"><a href="<%=request.getContextPath()%>/executiveController?action=logout">Logout</a></li>
  
  
</ul>

<%String s=(String)request.getAttribute("id"); %>
<div class="a"><h3 align="right">Welcome Executive!</h3></div>
<br><br><br><br><br>
<h1 align="center">Customer creation initiated successfully with id <%=s %></h1>
<br><br><br><br><br>
<%} %>
<%if(Esession==null||Esession.getAttribute("Role")==null||Esession.getAttribute("Role").equals("cashier")){ %>
<%response.sendRedirect("login.jsp"); %>
<%} %>
</body>
<%@include file="footer.jsp" %>
</html>