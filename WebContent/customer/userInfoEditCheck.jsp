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
この内容でよろしいですか？<br>
名前${name}<br>
住所${address}<br>
電話番号${tel}<br>
メールアドレス${id}<br>
パスワード${password}
<a href="customer/userInfoEdit.jsp">内容を修正する</a>
<form action="/intecs/UserInfoUpdateServelt" method="post">
<input type="hidden" name="name" value="${name}">
<input type="hidden" name="address" value="${address}">
<input type="hidden" name="tel" value="${tel}">
<input type="hidden" name="id" value="${id}">
<input type="hidden" name="password" value="${password}">
<input type="submit" value="上記内容で変更する">
</form>
<%@include file="footer.jsp" %>
</body>
</html>