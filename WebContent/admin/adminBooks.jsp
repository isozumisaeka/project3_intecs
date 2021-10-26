<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import = "inc.HIKARI.beans.BookBean" %>
<% List<BookBean> booklist = (List<BookBean>)request.getAttribute("booklist"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
<%@include file="adminHeader.jsp" %>
</head>
<body>
<h2>商品一覧</h2>
<hr>
<p>商品を検索<p>
<form action="/intecs/BookSearchServlet" action="get">
<input type="text" name="keyword" value="">
<input type="submit" name="btnSearch" value="検索">
</form>
<table>
	<tr>
		<th>ISBN</th>
		<th>書籍名</th>
		<th>発売日</th>
		<th>著者</th>
		<th>出版社</th>
		<th>価格</th>
		<th>在庫</th>
		<th>削除フラグ</th>
		<th>表紙</th>
	</tr>
	<% for(int i=0;i<booklist.size();i++){%>
	<% pageContext.setAttribute("i", i); %>
	<tr>
		<td>${booklist[i].isbn }</td>
		<td>${booklist[i].title }${booklist[i].volume }</td>
		<td>${booklist[i].publishedDate}</td>
		<td>${booklist[i].author }</td>
		<td>${booklist[i].publisher }</td>
		<td>${booklist[i].price }円</td>
		<td>
			<form action="/intecs/AdminBookStockServlet" method="get">
				<input type="hidden" name="isbn" value="${booklist[i].isbn }">
				<input type="text" name="stock" value="${booklist[i].stock }">
				<input type="submit" value="更新">
			</form>
		</td>
		<td>
			<form action="AdminBookDeleteServlet" method="get">
				<input type="submit" name="${booklist[i].isbn }" value="削除">
			</form>
		</td>
		<td><img src="../intecs/img/${booklist[i].isbn}.jpg" width="200" height="300"></td>
	</tr>
	<%}%>
</table>


<a href="admin_index.jsp">戻る</a>
</body>
</html>