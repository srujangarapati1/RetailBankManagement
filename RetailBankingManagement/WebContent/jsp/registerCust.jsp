<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/designexecutive.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Myscript.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<%HttpSession Esession=request.getSession(false); %>
<%if(Esession.getAttribute("Role")!=null&&Esession.getAttribute("Role").equals("executive")){ %>

<ul>
  <li><a href="<%=request.getContextPath()%>/executiveController?action=home">Home</a></li>
  <li><a href="<%=request.getContextPath()%>/executiveController?action=customer">Add Costumer</a></li>
  <li><a href="<%=request.getContextPath()%>/executiveController?action=account">Add Account</a></li>
  <li style="float:Right"><a href="<%=request.getContextPath()%>/executiveController?action=logout">logout</a></li>
  
  
</ul>
<div class="a"><h3 align="right">Welcome Executive!</h3></div>
<center>


<form method="post" action="<%=request.getContextPath() %>/executiveController" name="customerform" onsubmit="return customervalidate()">
<fieldset class="fieldset3"><br>
<table align="center">
<tr>
<th align="left">Customer SSN ID:</th><td><input type="text" name="ssnid" placeholder="Customer SSN ID" required><div id="di"><br></div></td></tr>
<tr><th align="left">

Customer Name:</th><td><input type="text" name="name" placeholder="Customer Name" required><br><div id="adi"></div></td></tr>
<tr><th align="left">

Age:</th><td><input type="text" name="age" placeholder="Age" required><br><div id="bdi"></div></td></tr>

<tr><th align="left">Address Line1:</th><td><input type="text" name="addLine1" placeholder="Address Line1" required></td></tr>

<tr><th align="left">Address Line2:</th><td><input type="text" name="addLine2" placeholder="Address Line2"></td></tr>

<tr><th align="left">City:</th><td><input type="text" name="city" placeholder="City" required><br><div id="cdi"></div></td></tr>
<tr><th align="left">State:</th><td><input type="text" name="state" placeholder="State" required><br><div id="ddi"></div></td></tr>
</table>

<input type="submit" name="action" class="button" value="register">
</fieldset>

</form>

</center>
<br><br><br><br><br><br>
<br><br><br><br><br><br>
<%} %>
<%if(Esession==null||Esession.getAttribute("Role")==null||Esession.getAttribute("Role").equals("cashier")){ %>
<%response.sendRedirect("login.jsp"); %>
<%} %>
</body>
<%@include file="footer.jsp" %>
</html>