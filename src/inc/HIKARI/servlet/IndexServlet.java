package inc.HIKARI.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inc.HIKARI.DAO.BookBeanDAO;
import inc.HIKARI.DAO.TaxBeanDAO;
import inc.HIKARI.beans.BookBean;
import inc.HIKARI.beans.TaxBean;


public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションを取得する
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		List<BookBean> booklist = BookBeanDAO.getRandomList();
		List<BookBean> ranking = BookBeanDAO.getRankingList();
		List<BookBean> newbook = BookBeanDAO.getNewList();
		request.setAttribute("booklist", booklist);
		request.setAttribute("ranking", ranking);
		request.setAttribute("newbook", newbook);
		//税を取得
		List<TaxBean> taxlist = TaxBeanDAO.getListByTax();
		request.setAttribute("taxlist", taxlist);
		
		String nextPage = "customer/index.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);


	}

}
