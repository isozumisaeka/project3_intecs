<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
    <%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="inc.HIKARI.beans.CustomerBean" %>
<%--  <%@include file="../css/style.css"  %> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 独自CSS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<!-- <link rel=”stylesheet” type=”text/css” href=”../css/style.css”> -->
<title>Insert title here</title>

</head>
<body>
<% List<CustomerBean> s = (List<CustomerBean>) session.getAttribute("login"); %>
ようこそ${login[0].name}さん
<a href="/intecs/customer/logout.jsp" >ログアウト</a>
<a href="/intecs/admin/adminIndex.jsp">管理者ページTOP</a>
<br>
</body>
</html>