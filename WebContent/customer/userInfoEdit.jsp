<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="header.jsp" %>
</head>
<body>

会員情報の変更
<hr>
${message}
<%session.removeAttribute("message"); %>

<form action="/intecs/UserInfoEditCheckServlet" method="post">
	名前<input type="text" name="name" value="${login[0].name}">
	<br>住所<input type="text" name="address" value="${login[0].address}">
	<br>電話番号<input type="text" name="tel" value="${login[0].tel}">
	<br>メールアドレス<input type="text" name="id" value="${login[0].id}">
	<br>パスワード※大文字、小文字、数字を含む半角英数字8文字以上16文字以下
	<input type="password" name="password" value="${login[0].password}">
	<br><input type="submit" value="確認画面へ進む">
</form>
<%@include file="footer.jsp" %>
</body>
</html>