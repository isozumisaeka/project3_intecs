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
 * Servlet implementation class AdminUserDeleteServlet
 */
public class AdminUserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを受け取る
		String id = request.getParameter("id");

		//処理メッセージ用意
		String message;

		//ページ遷移用意
		String nextPage;

		//DAO
		if (CustomerBeanDAO.updateCustomerFlg(id)) {

			//削除(削除フラグを0に変更)成功=================================
			//ログイン中のユーザーが削除された場合、強制ログアウト
			boolean loginFlg = false;
			HttpSession session = request.getSession();
			List<CustomerBean> login = (List<CustomerBean>)session.getAttribute("login");
			for(CustomerBean cb : login) {
				if (id==cb.getId()) {
					session.invalidate();
					loginFlg = true;
					break;
				}
			}
			//ログイン中のユーザー以外が削除された場合、adminListから情報を削除
			List<CustomerBean> adminList = (List<CustomerBean>)session.getAttribute("adminList");
			for(CustomerBean cb : adminList) {
				if (id==cb.getId()) {
					adminList.remove(cb);
					response.sendRedirect("customer/totalError.jsp");
				}
			}
			if (loginFlg) {
				nextPage = "customer/logoutDone.jsp";
			}else {
				nextPage = "/intecs/AdminUserAllServlet";
			}
			response.sendRedirect(nextPage);
			message = "削除しました。";
			session.setAttribute("message", message);
		}else {
			//削除失敗=================================
			response.sendRedirect("customer/totalError.jsp");
		}
	}
}
