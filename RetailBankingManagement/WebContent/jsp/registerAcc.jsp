<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/design.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/designexecutive.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Myscript.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

<%HttpSession Esession=request.getSession(false); %>
<%if(Esession.getAttribute("Role")!=null&&Esession.getAttribute("Role").equals("executive")){ %><ul >
  <li><a href="<%=request.getContextPath()%>/executiveController?action=home">Home</a></li>
  <li><a href="<%=request.getContextPath()%>/executiveController?action=customer">Add Costumer</a></li>
  <li><a href="<%=request.getContextPath()%>/executiveController?action=account">Add Account</a></li>
  <li style="float:Right"><a href="<%=request.getContextPath()%>/executiveController?action=logout">logout</a></li>
 
  
</ul>

<div class="a"><h3 align="right">Welcome Executive!</h3></div>
<center>
<br><br><br><br><br>
<form method="post" action="<%=request.getContextPath() %>/executiveController" name="accountform" onsubmit="return accountvalidate();">
<fieldset class="fieldset4" align="center"><br><br>
<table align="center">

<tr>
<th align="left">Account Type:<br><br></th><td><select  name="type" required>
<option value="Savings">Savings Account </option>
<option value="Current">Current Account</option>
</select><br><br></td></tr>

<tr>

<th align="left">Customer ID:<br><br></th><td><input type="text" name="custId" required><br><div id="di"></div><br></td></tr>


<tr><th align="left">Deposit amount:<br><br></th><td><input type="text" name="amount" required><br><div id="adi"></div><br></td></tr>
</table>

<input type="submit" name="action" class="button" value="addaccount">
</fieldset>

</form>
<br><br><br><br><br><br>
<br><br><br>
</center>
<%} %>
<%if(Esession==null||Esession.getAttribute("Role")==null||Esession.getAttribute("Role").equals("cashier")){ %>
<%response.sendRedirect("login.jsp"); %>
<%} %>
</body>
<%@include file="footer.jsp" %>
</html>