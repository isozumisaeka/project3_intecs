package inc.HIKARI.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inc.HIKARI.DAO.BookBeanDAO;
import inc.HIKARI.beans.BookBean;

/**
 * Servlet implementation class BookSearchServlet
 */
public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを受け取る
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		//staticメソッドを呼び出して結果を取得
		List<BookBean> booklist = BookBeanDAO.getBookList(keyword);
		request.setAttribute("booklist", booklist);

		String nextPage = "admin/adminBooks.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
