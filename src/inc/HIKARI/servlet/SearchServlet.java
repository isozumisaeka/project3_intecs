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

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを受け取る
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		//staticメソッドを呼び出して結果を取得
		List<BookBean> list = BookBeanDAO.getBookList(keyword);

		//取得したDBデータをリクエスト属性に格納
		request.setAttribute("list", list);

		//税を取得
		List<TaxBean> taxlist = TaxBeanDAO.getListByTax();
		request.setAttribute("taxlist", taxlist);

		//ページ遷移の変数を用意
		String nextPage;
		//取得データで遷移先を変更
		if(list.size()==0) {
			//該当ワードなし
			request.setAttribute("keyword", keyword);
			nextPage = "customer/searchError.jsp";
		}else {
			nextPage = "customer/searchResult.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

	}

}
