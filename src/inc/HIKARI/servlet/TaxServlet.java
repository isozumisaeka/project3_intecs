package inc.HIKARI.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inc.HIKARI.DAO.TaxBeanDAO;
import inc.HIKARI.beans.TaxBean;

public class TaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TaxBean> taxlist = TaxBeanDAO.getListByTax();

		String message = (String)request.getAttribute("message");
		if(message == null) {
			message = "";
		}
		request.setAttribute("message", message);
		request.setAttribute("taxlist", taxlist);
		String nextPage = "admin/adminTax.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
