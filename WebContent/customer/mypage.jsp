<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="header.jsp" %>
</head>
<body>

${login[0].name}さんのマイページ
<a href="userInfoEdit.jsp">会員情報変更</a>
<a href="../PurchasedHistorylistServlet">購入履歴</a>
<a href="quit.jsp">退会</a>
<%@include file="footer.jsp" %>
</body>
</html>