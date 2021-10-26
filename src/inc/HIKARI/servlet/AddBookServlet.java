package inc.HIKARI.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inc.HIKARI.DAO.BookBeanDAO;
import inc.HIKARI.beans.BookBean;

/**
 * Servlet implementation class AddBookServlet
 */
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nextPage = "BookSearchServlet";
		try {
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			int volume = Integer.parseInt(request.getParameter("volume"));

			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String publishedDateStr = year + "-" + month + "-" + day;
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date publishedDateDate = sfd.parse(publishedDateStr);
			long timeInMilliSeconds = publishedDateDate.getTime();
		    Date publishedDate = new Date(timeInMilliSeconds);

		    String author = request.getParameter("author");
		    String publisher = request.getParameter("publisher");
		    int price = Integer.parseInt(request.getParameter("price"));
		    int stock = 0;
		    String comment = "";
		    int deleteFlg = 1;
		    String image = "img/NowPrinting.png";
			BookBean newBook = new BookBean(isbn,title,volume,publishedDate,author,publisher,price,stock,comment,deleteFlg,image);

			if(BookBeanDAO.addBook(newBook)) {
				request.setAttribute("message","追加できました" );
			}else {
				request.setAttribute("message","追加できませんでした");
			}
		}catch(NumberFormatException e) {
			request.setAttribute("message", "半角数字で入力してください");
		} catch (java.text.ParseException e) {
			request.setAttribute("message", "正しく入力してください");
		}finally {
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request,response);
		}
	}
}
