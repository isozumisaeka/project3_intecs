<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
退会手続き
<hr>
${login[0].name}さん、
本当に退会しますか？<br>
<form action="/intecs/QuitServlet" method="post">
<input type="submit" value="退会する">
</form>
<%@include file="footer.jsp" %>
</body>
</html>