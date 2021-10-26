<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="inc.HIKARI.beans.BookBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>検索結果</h1>
<hr>
<div>
<% List<BookBean> list = (List<BookBean>)request.getAttribute("list"); %>
<% for(int i = 0;i<list.size();i++){%>
<% pageContext.setAttribute("i", i); %>
  <div>
      <a href="/intecs/DetailServlet?bookisbn=${list[i].isbn}">
      <p><img src="../intecs/img/${list[i].isbn}.jpg" width="200" height="300"></p>
      <p>${list[i].title}${list[i].volume}</p>
      <p>${list[i].author}</p>
      <p>${list[i].price+ Math.round(Math.floor(list[i].price *taxlist[0].taxRate))}円</p>
      </a>

<form action="CartInsertServlet">
	<select name="count">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
		<option value="9">9</option>
		<option value="10">10</option>
	</select>

	<input type="hidden" value="${list[i].isbn}" name="isbn">
	<input type="hidden" value="${list[i].title}" name="title">
	<input type="hidden" value="${list[i].volume}" name="volume">
	<input type="hidden" value="${list[i].publishedDate}" name="publishedDate">
	<input type="hidden" value="${list[i].author}" name="author">
	<input type="hidden" value="${list[i].publisher}" name="publisher">
	<input type="hidden" value="${list[i].price}" name="price">
	<input type="hidden" value="${list[i].stock}" name="stock">
	<input type="hidden" value="${list[i].comment}" name="comment">
	<input type="hidden" value="${list[i].deleteFlg}" name="deleteFlg">
	<input type="hidden" value="${list[i].image}" name="image">

	<input type="submit" value="カートに追加">
</form>

  </div>
<%}%>
</div>
<hr>
<form action="index.jsp" method="get">
<input type="submit" value="TOPへ">
</form>
<%@include file="footer.jsp" %>
</body>
</html>