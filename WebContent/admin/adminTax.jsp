<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(request.getParameter("tax") != null){System.out.println("get");} %>
<%@ page import="java.util.*" %>
<%@ page import = "inc.HIKARI.beans.TaxBean" %>
<% Calendar cl =Calendar.getInstance(); %>
<% int year = cl.get(Calendar.YEAR);%>
<% int month = cl.get(Calendar.MONTH) + 1;%>
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
<% List<TaxBean> taxlist = (List<TaxBean>)request.getAttribute("taxlist"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/calendar.js" type="text/javascript"></script>
<title>Insert title here</title>
<%@include file="adminHeader.jsp" %>
</head>
<body>
	<h2>=========header==========</h2>
	<p>管理者ページ-消費税率の登録</p>
	${message}
	<table>
	<% for(int i=0;i<taxlist.size();i++){%>
	<%pageContext.setAttribute("i",i); %>
		<tr>
			<th>税率</th>
			<td>${taxlist[i].tax }%</td>
			<th>適用開始日</th>
			<td>${taxlist[i].startTax }</td>
		</tr>
	<%} %>
	</table>
	<form action="AddTaxServlet" method="get">
		税率<input type="text" name="tax" placeholder="10">%　
		適用開始日
		<select id="year" name="year" onchange="calendar()">
		<%for(int i=0;i<=50;i++){%>
			<option value="<%=year + i%>"><%=year + i%>年</option>
		<%}%>
		</select>
		<select id="month" name="month" onchange="calendar()">
		<%for(int i=1;i<=12;i++){%>
			<option value="<%=i%>"<%if(month==i){out.print(" selected");}%>><%=i%>月</option>
		<%}%>
		</select>
		<select id="day" name="day">
		<%for(int i=1;i<=maxday;i++){%>
			<option value="<%=i%>"<%if(day==i){out.print(" selected");}%>><%=i%>日</option>
		<%}%>
		</select>
		<input type="submit" value="送信">
	</form>
</body>
</html>