<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="header.jsp" %>
</head>
<body>
<p><img src="../intecs/img/${book[0].isbn}.jpg" width="400" height="600"></p>
<p>${book[0].title}${book[0].volume}</p>
<p>${book[0].publishedDate}</p>
<p>${book[0].author}</p>
<p>${book[0].publisher}</p>
<p>${book[0].comment}</p>
<p>${book[0].price+ Math.round(Math.floor(book[0].price *taxlist[0].taxRate))}円</p>

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

	<input type="hidden" value="${book[0].isbn}" name="isbn">
	<input type="hidden" value="${book[0].title}" name="title">
	<input type="hidden" value="${book[0].volume}" name="volume">
	<input type="hidden" value="${book[0].publishedDate}" name="publishedDate">
	<input type="hidden" value="${book[0].author}" name="author">
	<input type="hidden" value="${book[0].publisher}" name="publisher">
	<input type="hidden" value="${book[0].price}" name="price">
	<input type="hidden" value="${book[0].stock}" name="stock">
	<input type="hidden" value="${book[0].comment}" name="comment">
	<input type="hidden" value="${book[0].deleteFlg}" name="deleteFlg">
	<input type="hidden" value="${book[0].image}" name="image">

	<input type="submit" value="カートに追加">
</form>
<%@include file="footer.jsp" %>
</body>
</html>