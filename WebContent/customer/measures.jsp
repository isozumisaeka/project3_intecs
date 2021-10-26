

<%
String dummy = (String)request.getAttribute("dummy");
 out.println(dummy);
if(dummy == null){
String nextPage = "totalError.jsp";
RequestDispatcher rd = request.getRequestDispatcher(nextPage);
rd.forward(request, response);
}

%>
