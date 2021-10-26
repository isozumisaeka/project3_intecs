package inc.HIKARI.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inc.HIKARI.DAO.CustomerBeanDAO;
import inc.HIKARI.beans.CustomerBean;


public class AdminUserSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを受け取る
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		//セッションの取得
		HttpSession session = request.getSession();
		//カスタマーオブジェクトを作成（アドミンオブジェクト？）
		CustomerBean cb = new CustomerBean();
		cb.setName(name);
		cb.setId(id);
		cb.setPassword(password);
		String nextPage;
		//登録
		if(CustomerBeanDAO.addAdminUser(cb)) {
			nextPage="admin/adminSignupDone.jsp";
		}else {
			nextPage="admin/adminSignupError.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
