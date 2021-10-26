<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>アドミンユーザー新規登録</h2>
<form action="../AdminUserSignupServlet" method="post">
ユーザーネーム：<input type="text" name="name" value="">
ユーザーID：<input type="text" name="id" value="">
パスワード：<input type="text" name="password" value="">
<input type="submit" value="登録">
</form>
</body>
</html>