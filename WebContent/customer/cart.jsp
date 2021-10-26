<%@page import="java.awt.print.Printable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="inc.HIKARI.beans.CartBean" %>
<%@ page import="inc.HIKARI.DAO.BookBeanDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="header.jsp" %>
</head>
<body>
買い物カートには以下の商品が入っています。
<hr>
${insertMessage}
<%session.removeAttribute("insertMessage"); %>

${deleteMessage}
<%session.removeAttribute("deleteMessage"); %>

<%
	if (session.getAttribute("login")==null) {
		//ログインしてなかったらloginAnnounce.jspへ遷移
		response.sendRedirect("/intecs/customer/loginAnnounce.jsp");
	}else{
%>

	<% List<CartBean> cart = (List<CartBean>)session.getAttribute("cart"); %>
	<% pageContext.setAttribute("cart", cart); %>

	<%
	if(cart==null){
		response.sendRedirect("cartNull.jsp");
	}else if(cart.size()==0){ %>
	<p>買い物カートには商品が入っていません。</p>

	<% }else{ %>
		<% for(int i = 0;i<cart.size();i++){%>
			<% pageContext.setAttribute("i", i); %>

			<p><img  src="/intecs/img/${cart[i].isbn}.jpg" width="250" height="350"></p>
			<p>${cart[i].title}${cart[i].volume}</p>
			<p>${cart[i].author}</p>
			<p>${cart[i].price}円 </p>
			<p> ${cart[i].count}点</p>

			<!--できたらやりたかった数量変更の残骸
			<form action="" method="get">
				<input type="text"  name="cartInsert" value="${cart[i].count}">
				<input type="submit" value="数量変更">
			</form>
			-->

			<%
			CartBean cb = cart.get(i);
			String isbn = cb.getIsbn();
			int stock = BookBeanDAO.getBookStock(isbn);
			%>
			在庫 残り<%=stock %>点

			<form action="/intecs/BookDeleteServlet" method="get">
				<input type="hidden" name="cartNumber" value="<%= i %>">
				<input type="submit" value="削除" name="delete">
			</form>
			<hr>
		<%}%>
	<%}%>

<%}%>
<a href="order.jsp">レジに進む</a>
<form action="/intecs/BookDeleteServlet" method="get">
	<input type="submit" value="すべてクリア" name="delete">
</form>
<a href="/intecs/IndexServlet">TOPへ</a>
<%@include file="footer.jsp" %>
</body>
</html>