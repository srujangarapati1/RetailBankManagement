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
<%if(Esession.getAttribute("Role")!=null&&Esession.getAttribute("Role").equals("cashier")){ %><ul >
  <li><a href="<%=request.getContextPath()%>/cashierController?action=home">Home</a></li>
  <li><a href="<%=request.getContextPath()%>/cashierController?action=trans">Transfer amount</a></li>
  <li><a href="<%=request.getContextPath()%>/cashierController?action=statement">Account Statement</a></li>
  <li style="float:Right"><a href="<%=request.getContextPath()%>/cashierController?action=logout">logout</a></li>
 
  
</ul>
<div class="a"><h3 align="right">Welcome Cashier!</h3></div>
<br><br><br>
<div class="container">
<a href="<%=request.getContextPath() %>/cashierController?action=trans"><button class="btn">Transfer Amount</button></a>
<a href="<%=request.getContextPath() %>/cashierController?action=statement"><button class="btn1">Account Statement</button></a>
</div>

<br><br><br>
<%} %>
<%if(Esession==null||Esession.getAttribute("Role")==null||Esession.getAttribute("Role").equals("executive")){ %>
<%response.sendRedirect("login.jsp"); %>
<%} %>
</body>
<%@include file="footer.jsp" %>
</html>