<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
    <%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="inc.HIKARI.beans.CustomerBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="adminHeader.jsp" %>
</head>
<body>
<!-- 本文 -->
管理者ページ-TOP
<hr>
<a href="/intecs/AdminBookAllServlet">書籍情報の更新・削除</a>
<a href="/intecs/admin/adminBooksInsert.jsp">書籍情報の登録</a>
<a href="/intecs/AdminUserAllServlet">ユーザー登録情報</a>
<a href="/intecs/TaxServlet">消費税率の税率</a>
<a href="/intecs/AdminOrderListServlet">注文履歴</a>
</body>
</html>