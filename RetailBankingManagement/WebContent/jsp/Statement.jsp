<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Myscript.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/designexecutive.css">
<title>Insert title here</title>
</head>
<body>
<%HttpSession Esession=request.getSession(false); %>
<%if(Esession.getAttribute("Role")!=null&&Esession.getAttribute("Role").equals("cashier")){ %><ul >
  <li><a href="<%=request.getContextPath()%>/cashierController?action=home">Home</a></li>
  <li><a href="<%=request.getContextPath()%>/cashierController?action=trans">Transfer Amount</a></li>
  <li><a href="<%=request.getContextPath()%>/cashierController?action=statement">Account Statement</a></li>
  <li style="float:Right"><a href="<%=request.getContextPath()%>/cashierController?action=logout">logout</a></li>
    
  
</ul>
<div class="a"><h3 align="right">Welcome Cashier!</h3></div>
<br><br><br><br><br>
<center>
<fieldset class="fieldset2">
<form action="<%=request.getContextPath()%>/cashierController" method="post" name="statementform" onsubmit="return statementvalidate()">
<table>
<tr><td>Account Number:</td><td>
<input type="text" name="accountid" placeholder="enter accountid" required><br><div id="di"></div></td></tr><tr><td>Select No Of Statements</td><td>
<select name="count">
<%for(int i=1;i<=10;i++){ %>
<option value=<%=i %>><%=i %></option>
<%} %>
</select></td></tr>
<tr><td colspan="2" align="center">
<input type="submit" name="action" value="getstatement" class="button"></td></tr>
</table>
</form>
</fieldset>
</center>
<%} %>
<%if(Esession==null||Esession.getAttribute("Role")==null||Esession.getAttribute("Role").equals("executive")){ %>
<%response.sendRedirect("login.jsp"); %>
<%} %>
</body>
<%@include file="footer.jsp" %>
</html>