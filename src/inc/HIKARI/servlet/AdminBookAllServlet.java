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

public class AdminBookAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//int型のままlistに入れるとリクエスト属性に格納できないから、
		//List<PurchaseBean>型にする
		List<BookBean> booklist = BookBeanDAO.getListByBook();

		//取得したDBデータをリクエスト属性に格納
		request.setAttribute("booklist", booklist);
		//遷移先ぺージ準備
		String nextPage;
		if (booklist.size()==0) {
			nextPage = "customer/totalError.jsp";
		}
		else {
			nextPage = "admin/adminBooks.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);











	}

}
