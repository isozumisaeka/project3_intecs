<%@page import="java.awt.print.Printable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="inc.HIKARI.DAO.PurchaseBeanDAO" %>
<%@ page import="inc.HIKARI.beans.PurchaseBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="header.jsp" %>
</head>
<body>

<% List<PurchaseBean> list = (List<PurchaseBean>)request.getAttribute("list"); %>

<% if(list.size()==0){ %>
<p>購入履歴はありません。</p>

<% }else{ %>
	<% for(int i =0; i<list.size(); i++){%>
		<% pageContext.setAttribute("i", i); %>
		<p>ご購入日：${list[i].purchaseDate}</p>
		<p>ご注文番号：${list[i].purchaseId}</p>
		<p>支払方法：${list[i].howToPay}</p>
		<p>税込金額：${list[i].priceInTax}</p>
		<p>税抜合計：${list[i].priceTotal}</p>
		<p>税額合計：${list[i].taxTotal}</p>
		<p>送料：${list[i].postage}</p>
		<p>タイトル：${list[i].title}</p>
		<p>著者：${list[i].author}</p>
		<p>単価：${list[i].priceSolo}</p>
		<hr>
	<%}%>

<%}%>

<%@include file="footer.jsp" %>
</body>
</html>