package inc.HIKARI.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inc.HIKARI.DAO.CustomerBeanDAO;
import inc.HIKARI.beans.CustomerBean;

/**
 * Servlet implementation class AdminUserUpdateServlet
 */
public class AdminUserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//リクエストパラメータを受け取る
		request.setCharacterEncoding("UTF-8");
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		//DAO
		boolean flg = CustomerBeanDAO.updateCustomerInfo(name, address, tel, id, password, customerId);

		//処理メッセージ用意
		String message;

		if (flg) {
			//登録情報の更新完了
			//ログイン中の管理者ユーザー情報が変更された場合は変更内容をloginセッションに反映
			List<CustomerBean> login =(List<CustomerBean>)session.getAttribute("login");
			for(CustomerBean cb : login) {
				if (customerId == cb.getCustomerId()) {
					cb.setId(id);
					cb.setPassword(password);
					break;
				}
			}
			//更新完了メッセージ
			message = "更新完了しました。";
		}else {
			//登録情報の変更失敗
			message = "更新失敗しました。";
		}
		session.setAttribute("message", message);
		response.sendRedirect("AdminUserAllServlet");
	}
}
