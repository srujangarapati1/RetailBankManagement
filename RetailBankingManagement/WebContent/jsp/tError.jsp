<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<br><br><br>
<h1>Transfer not allowed, please choose smaller amount</h1>
<br><br><br>
<%} %><%if(Csession==null||Csession.getAttribute("Role")==null||Csession.getAttribute("Role").equals("executive")){ %>
<%response.sendRedirect("login.jsp"); %>
<%} %>
</body>
<%@include file="footer.jsp" %>

</html>