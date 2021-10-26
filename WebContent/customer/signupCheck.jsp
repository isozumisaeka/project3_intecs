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
	名前：${customer.name}<br>
	住所：${customer.address}<br>
	電話番号：${customer.tel}<br>
	メールアドレス：${customer.id}<br>
	パスワード：${customer.password}
<br>
<a href="customer/signup.jsp">内容を修正する</a>
<form action="SignupInsertServlet" method="post">
<input type="submit" value="この内容で登録する">
</form>
<%@include file="footer.jsp" %>
</body>
</html>