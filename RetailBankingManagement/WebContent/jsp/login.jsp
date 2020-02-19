<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

     
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/designexecutive.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Myscript.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="header.jsp" %>
<br><br><br>
<form method="post" action="<%=request.getContextPath() %>/Controller"name="loginform" onsubmit="return loginvalidate();">
<center>
<fieldset class="fieldset">


<table align="center">
<tr>

<th align="left" style="font-size:20px" >  User Id:<br><br> </th><td> <input type="text" id="empid" name="userId"  placeholder="User Id" align="right" required><br><div id="di"></div><br></td></tr>
<tr>
<th align="left" style="font-size:20px">   Password: </th><td><input type="password" id="password" name="password" placeholder="Enter Password" align="right" required><br><div id="di1"></div></td>
</tr>
</table>


<input type="submit" name="action" value="login" class="button">
</fieldset>
</center>
</form>


<br><br><br>
<%@include file="footer.jsp" %>
</body>

</html>