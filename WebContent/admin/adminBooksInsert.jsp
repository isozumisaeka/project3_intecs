<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import = "inc.HIKARI.beans.BookBean" %>
<% List<BookBean> booklist = (List<BookBean>)request.getAttribute("booklist"); %>
<% Calendar cl =Calendar.getInstance(); %>
<% int year = cl.get(Calendar.YEAR);%>
<% int month = cl.get(Calendar.MONTH);%>
<% int day = cl.get(Calendar.DATE);%>
<%
/* その月の最終日を設定
* 基本は31日
* 4.6.9.11は30日
* 2月は28日
* ただし、4の倍数の年は閏年、100の倍数の年は平年、400の倍数の年は閏年
*/
int maxday = 31;
switch (month){
	case 4:
	case 6:
	case 9:
	case 11:
		maxday = 30;
		break;

	case 2:
		maxday = 28;
		if(year%4 == 0){
			maxday = 29;
		}
		if (year%100 == 0){
			maxday = 28;
		}
		if(year%400 == 0){
			maxday = 29;
		}
		break;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="adminHeader.jsp" %>
</head>
<body>
<h2>管理者ページ-書籍情報の登録</h2>
<hr>
<form action="AddBookServlet" method="get">
<table>
	<tr>
		<th>ISBN</th>
		<th>書籍名</th>
		<th>巻数</th>
		<th>発売日</th>
		<th>著者</th>
		<th>出版社</th>
		<th>価格</th>
		<th>在庫</th>
		<th>あらすじ</th>
		<!--  <th>表紙</th>  -->
	</tr>
	<tr>
		<td><input type="text" name="isbn"></td>
		<td><input type="text" name="title"></td>
		<td><input type="text" name="volume"></td>
		<td>
		<select id="year" name="year" onchange="calendar()">
		<%for(int i=1950;i<=year+30;i++){%>
			<option value="<%=i%>"<%if(year==i){out.print(" selected");}%>><%=i%>年</option>
		<%}%>
		</select>
		<select id="month" name="month" onchange="calendar()">
		<%for(int i=1;i<=12;i++){%>
			<option value="<%=i%>"<%if(month==i){out.print(" selected");}%>><%=i%>月</option>
		<%}%>
		</select>
		<select id="day" name="day">
		<%for(int i=1;i<=31;i++){%>
			<option value="<%=i%>"<%if(day==i){out.print(" selected");}%>><%=i%>日</option>
		<%}%>
		</select>
		</td>
		<td><input type="text" name="author"></td>
		<td><input type="text" name="publisher"></td>
		<td><input type="text" name="price"></td>
		<td><input type="text" name="stock"></td>
		<td><input type="text" name="comment"></td>
	</tr>
</table>
<input type="submit" value="送信">
</form>

<a href="admin_index.jsp">戻る</a>
</body>
</html>