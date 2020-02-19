<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/designexecutive.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Myscript.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<center>
<fieldset class="fieldset1" align="center">
<form method="post" action="<%=request.getContextPath() %>/cashierController" name="transferform" onsubmit="return transfervalidate()"><br>

<table align="center">
<tr>
<th align="left">Transfer amount: <br><br> </th><td><input type="text" placeholder="Transfer amount" name="tAmount" required><br><div id="di"></div><br><br></td></tr>
<tr><th align="left">Source Account:<br><br></th><td><input type="text" placeholder="Source Account" name="sAcc" required><br><div id="adi"></div><br></td></tr>
<tr><th align="left">Target Account:<br><br></th><td><input type="text" placeholder="Target Account" name="tAcc" required><br><div id="bdi"></div><br></td></tr>
</table>
<input type="submit" name="action"  value="transfer" class="button">
</fieldset>
</center>
</tr>

</form>
</fieldset>
<center>
<br><br><br>
</body>
<%} %><%if(Csession==null||Csession.getAttribute("Role")==null||Csession.getAttribute("Role").equals("executive")){ %>
<%response.sendRedirect("login.jsp"); %>
<%} %>
<%@include file="footer.jsp" %>

</html>