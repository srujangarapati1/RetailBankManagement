<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/design.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Myscript.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br><br><br>


<form method="post" action="<%=request.getContextPath() %>/Controller"name="loginform" onsubmit="return loginvalidate();">
<center>
<fieldset class="fieldset" align="center">
<h5 align="center">
<table align="center">
<tr>

<th align="left">  User Id:<br><br> </th><td> <input type="text" id="empid" name="userId" placeholder="User Id" align="right" required><br><br></td></tr>
<tr>
<th align="left">Password:</th><td><input type="password" id="password" name="password" placeholder="enter the Password" align="right" required><br></td>
</tr>

</table>
</h5>

<input type="submit" name="action" value="login" class="button">
</fieldset>
</center>
</form>

<p style="color:red" align="center">wrong password or user Id</p>

<br><br><br>
</body>
<%@include file="footer.jsp" %>
</html>