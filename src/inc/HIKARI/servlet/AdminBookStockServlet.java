package inc.HIKARI.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inc.HIKARI.DAO.BookBeanDAO;
public class AdminBookStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nextPage = "BookSearchServlet";
		try {
			String isbn = request.getParameter("isbn");
			int stock = Integer.parseInt(request.getParameter("stock"));

			if(BookBeanDAO.updateStock(isbn,stock)) {
				request.setAttribute("message","追加できました" );
			}else {
				request.setAttribute("message","追加できませんでした");
			}
		}catch(NumberFormatException e) {
			request.setAttribute("message", "半角数字で入力してください");
		}finally {
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request,response);
		}
	}
}
