<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="inc.HIKARI.beans.CartBean" %>
<%@ page import="inc.HIKARI.beans.CustomerBean" %>
<%@ page import="inc.HIKARI.beans.TaxBean" %>
<%@ page import="inc.HIKARI.DAO.TaxBeanDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="header.jsp" %>
<!-- @include file="measures.jsp"   -->
<%
	//セッション属性からの取得
	//カートを取得
	List<CartBean> cart = (List<CartBean>)session.getAttribute("cart");
	//ユーザー情報を取得
	List<CustomerBean> user = (List<CustomerBean>)session.getAttribute("user");
	pageContext.setAttribute("user", user);
	List<TaxBean> taxlist = TaxBeanDAO.getListByTax();
	request.setAttribute("taxlist", taxlist);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/intecs/PurchasedServlet">
<h2>注文内容のご確認</h2>
<hr>
<p>お届け先：${user[0].address}</p>
<p>お支払方法：
<select name="howtopay">
	<option value="代金引換">代金引換</option>
	<option value="銀行振込">銀行振込</option>
	<option value="クレジットカード">クレジットカード</option>
</select></p>
<div>
	<% for(int i = 0;i<cart.size();i++){%>
	<% pageContext.setAttribute("i", i); %>
<div>
<p><img  src="/intecs/img/${cart[i].isbn}.jpg" width="250" height="350"></p>
<p>${cart[i].title}${cart[i].volume}</p>
<p>${cart[i].author}</p>
<p>${cart[i].price+ Math.round(Math.floor(cart[i].price *taxlist[0].taxRate))}円</p>
<p>${cart[i].count}点</p>
</div>
	<%} %>
</div>
<a href="/intecs/IndexServlet" >TOPへ</a>
<a href="cart.jsp">カートへ</a>
<input type="submit" value="ご注文を確定する">
</form>
<%@include file="footer.jsp" %>
</body>
</html>