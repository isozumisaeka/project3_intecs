<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
<h2>おすすめ</h2>
<div class="wrapper">
<% for(int i = 0;i<14;i++){%>
<% pageContext.setAttribute("i", i); %>
  <div class="book">
      <a href="DetailServlet?bookisbn=${booklist[i].isbn}">
      <div class="book_img">
      <img  src="../intecs/img/${booklist[i].isbn}.jpg" width="250" height="350">
      </div>
      <p>${booklist[i].title}${booklist[i].volume}</p>
      <p>${booklist[i].author}</p>
      <p>${booklist[i].price + Math.round(Math.floor(booklist[i].price *taxlist[0].taxRate))}円</p>

      </a>
  </div>
<%}%>
  </div>
<h2>ランキング</h2>
<div class="wrapper">
<% for(int i = 0;i<14;i++){%>
<% pageContext.setAttribute("i", i); %>
  <div class="book">
       <a href="DetailServlet?bookisbn=${ranking[i].isbn}">
       <div class="book_img">
	      <img src="../intecs/img/${ranking[i].isbn}.jpg" width="200" height="300">
      </div>
      <div class="book_info">
	      ${ranking[i].title}${ranking[i].volume}
	      ${ranking[i].author}
	      ${ranking[i].price + Math.round(Math.floor(ranking[i].price *taxlist[0].taxRate))}円
      </div>
      </a>
  </div>
<%}%>
</div>

<h2>新作</h2>
 <div class="wrapper">
<% for(int i = 0;i<14;i++){%>
<% pageContext.setAttribute("i", i); %>
  <div class="book">
       <a href="DetailServlet?bookisbn=${newbook[i].isbn}">
      <p><img src="../intecs/img/${newbook[i].isbn}.jpg" width="200" height="300"></p>
      <p>${newbook[i].title}${newbook[i].volume}</p>
      <p>${newbook[i].author}</p>
      <p>${newbook[i].price + Math.round(Math.floor(newbook[i].price *taxlist[0].taxRate))}円</p>
      </a>
  </div>
<%}%>
</div>




</body>
</html>