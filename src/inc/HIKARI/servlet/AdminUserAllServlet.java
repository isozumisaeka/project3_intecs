package inc.HIKARI.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inc.HIKARI.DAO.CustomerBeanDAO;
import inc.HIKARI.beans.CustomerBean;

/**
 * Servlet implementation class AdminUserAllServlet
 */
public class AdminUserAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//前田記述分====================================================
		//現在登録されている管理者ユーザー情報を取得してlistに格納
		List<CustomerBean> list1 = CustomerBeanDAO.getAdminUserList();

		//セッションに追加
		HttpSession session = request.getSession();
		session.setAttribute("adminList", list1);

		//ページ遷移
		//response.sendRedirect("admin/adminUser.jsp");



		//五十住さん記述分====================================================
		//セッションの取得
		//HttpSession session = request.getSession();
		//カスタマーオブジェクトを生成
		List <CustomerBean> list2 = CustomerBeanDAO.getAllUsers();

		String nextPage;

		if(list2.size()!=0) {
			//表示成功
			request.setAttribute("list", list2);
			nextPage= "admin/adminUser.jsp";
		}else {
			nextPage="admin/adminIndex.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
