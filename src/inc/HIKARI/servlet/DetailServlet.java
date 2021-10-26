package inc.HIKARI.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inc.HIKARI.DAO.BookBeanDAO;
import inc.HIKARI.DAO.TaxBeanDAO;
import inc.HIKARI.beans.BookBean;
import inc.HIKARI.beans.TaxBean;

/**
 * Servlet implementation class DetailServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bookisbn = request.getParameter("bookisbn");

		List<BookBean> book = BookBeanDAO.getBookDetail(bookisbn);

		request.setAttribute("book", book);
		//税を取得
		List<TaxBean> taxlist = TaxBeanDAO.getListByTax();
		request.setAttribute("taxlist", taxlist);
		String nextPage = "customer/detail.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
