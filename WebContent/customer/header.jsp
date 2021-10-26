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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<title>Insert title here</title>

</head>
<body>
<div class="header">
<a href="/intecs/IndexServlet">intecs</a>
<form action="/intecs/SearchServlet" action="get">
<input type="text" name="keyword" value="">
<input type="submit" name="btnSearch" value="検索">
</form>
<% List<CustomerBean> s = (List<CustomerBean>) session.getAttribute("login");
if(s == null){ %>
<!-- ログインしていなかったら -->
ようこそゲストさん。
<a href="/intecs/customer/login.jsp" >ログイン</a>
<%}else{ %>
<!-- ログインしていたら -->
ようこそ${login[0].name}さん！
<a href="/intecs/customer/logout.jsp" >ログアウト</a>
<a href="/intecs/customer/cart.jsp" ><img src="../images/shopping-cart.png" style="width:20px"></a>
<a href="/intecs/customer/mypage.jsp" >マイページ</a>
<%} %>
</div>
</body>
</html>