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

	<!-- できたらやる ログイン時はログアウト画面、未ログイン時はログイン画面を表示 -->
	<form action="/intecs/LoginServlet" method="post">
		メールアドレス(id)<input type="text" name="id">
		パスワード<input type="password" name="password">
		<input type="submit" value="ログイン">
	</form>
	新規会員登録は<a href="signup.jsp">こちら</a>
	<%@include file="footer.jsp" %>
</body>
</html>