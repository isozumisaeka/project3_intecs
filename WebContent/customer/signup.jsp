<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
新規会員登録
<hr>
	<form action="../SignupCheckServlet" method="post">
		名前<br>
		<input type="text" name="name" value=""><br>
		住所<br>
		<input type="text" name="address" value=""><br>
		電話番号<br>
		<input type="text" name="tel" value=""><br>
		メールアドレス<br>
		<input type="text" name="id" value=""><br>
		パスワード<br>
		※大文字、小文字、数字を含む半角英数字8文字以上16文字以下<br>
		<input type="text" name="password" value=""><br>
		<input type="submit" value="確認画面へ進む">
	</form>
<%@include file="footer.jsp" %>
</body>
</html>