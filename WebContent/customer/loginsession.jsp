
<%@ page import="java.util.*" %>
<%@ page import="inc.HIKARI.beans.CustomerBean" %>
<%
//セッション取得
List<CustomerBean> usercheak = (List<CustomerBean>)session.getAttribute("user");

if(usercheak == null){
String nextPage = "totalError.jsp";
RequestDispatcher rd = request.getRequestDispatcher(nextPage);
rd.forward(request, response);
}
%>
