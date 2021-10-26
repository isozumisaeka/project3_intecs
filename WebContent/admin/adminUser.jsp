<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="inc.HIKARI.beans.CustomerBean" %>
<%@ page import="inc.HIKARI.DAO.CustomerBeanDAO" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="adminHeader.jsp" %>
<h2>管理者ページ-ユーザー登録情報</h2>
<hr>
<!-- 五十住さん記述分 -->
<a href="admin/adminUserSignup.jsp">管理者ユーザー新規登録</a>
<!-- 前田記述分 -->
<h3>管理者ユーザー一覧</h3>
${message}
<%session.removeAttribute("message"); %>

<% List<CustomerBean> adminList = (List<CustomerBean>)session.getAttribute("adminList"); %>
<% for(int i = 0;i<adminList.size();i++){%>
<% pageContext.setAttribute("i", i); %>
	<form action="/intecs/AdminUserUpdateServlet" method="post">
		管理者ID<input type="text" name="id" value="${adminList[i].id }">
		管理者パスワード<input type="password" name="password" value="${adminList[i].password }">

		<input type="hidden" name="customerId" value="${adminList[i].customerId }">
		<input type="hidden" name="name" value="${adminList[i].name }">
		<input type="hidden" name="address" value="${adminList[i].address }">
		<input type="hidden" name="tel" value="${adminList[i].tel }">

		<br>
		<input type="submit" value="更新">
	</form>

	<form action="/intecs/AdminUserDeleteServlet" method="post">
		<input type="hidden" name="id" value="${adminList[i].id }">
		<input type="submit" value="削除">
	</form>
	<hr>
<%}%>
<h3>全ユーザー一覧</h3>
<div>
<% List<CustomerBean> list = (List<CustomerBean>)request.getAttribute("list"); %>
<% for(int i = 0;i<list.size();i++){%>
<% pageContext.setAttribute("i", i); %>
  <div>
      <p>${list[i].customerId}</p>
      <p>名前：${list[i].name}</p>
      <p>パスワード：${list[i].password}</p>
      <p>メールアドレス（id）：${list[i].id}</p>
      <p>ユーザー状態：${list[i].customerFlg}（0:退会 1:既存 2:アドミン）</p>
      <p>住所：${list[i].address}</p>
      <p>電話番号：${list[i].tel}</p>
      <hr>
  </div>
<%}%>
  </div>
  <a href="admin/adminIndex.jsp">管理者ページTOPへ</a>
</body>
</html>