<%@page import="com.bean.Transfer"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/designexecutive.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%HttpSession Esession=request.getSession(false); %>
<%if(Esession.getAttribute("Role")!=null&&Esession.getAttribute("Role").equals("cashier")){ %>

<ul>
  <li><a href="<%=request.getContextPath()%>/cashierController?action=home">Home</a></li>
  <li><a href="<%=request.getContextPath()%>/cashierController?action=trans">Transfer Amount</a></li>
  <li><a href="<%=request.getContextPath()%>/cashierController?action=statement">Account Statement</a></li>
  <li style="float:Right"><a href="<%=request.getContextPath()%>/cashierController?action=logout">logout</a></li>
  
</ul>
<div class="a"><h3 align="right">Welcome Cashier!</h3></div>
<br><br><br>
<%ArrayList<Transfer> list=(ArrayList<Transfer>)request.getAttribute("list");
 Integer i=(Integer)request.getAttribute("aid");
 String c="credit";
 String d="debit";%>
 <center>
 <fieldset class="fieldset" align="center">
<table border=1><tr>
<th>Source ID</th><th>Target ID</th><th>Credit/Debit</th><th>Amount</th><th>Date of Transaction</th><th>Time</th></tr>
<%for(Transfer t:list){ %>
<tr><td><%=t.getSource() %></td>
<td><%=t.getTarget() %></td>
<%if(t.getSource()==i){ %>
<td><%=d %></td>
<%}%>
<%if(t.getTarget()==i){ %>
<td><%=c %></td>
<%} %>

<td><%=t.getAmount() %></td><td><%=t.getDate() %></td><td><%=t.getTime() %></td></tr>
<%} %>
</table>

</fieldset>
</center>
<%} %>
<%if(Esession==null||Esession.getAttribute("Role")==null||Esession.getAttribute("Role").equals("executive")){ %>
<%response.sendRedirect("login.jsp"); %>
<%} %>
</body>

<%@include file="footer.jsp" %>
</html>