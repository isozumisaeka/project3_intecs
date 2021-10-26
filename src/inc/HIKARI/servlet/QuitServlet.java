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
 * Servlet implementation class QuitServlet
 */
public class QuitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション取得
		HttpSession session = request.getSession();
		//まずオブジェクトをCustomerBean型で取得してから

		List<CustomerBean> cb = (List<CustomerBean>) session.getAttribute("login");

		for (CustomerBean check : cb) {
			String id = check.getId();

			//ページ遷移準備
			String nextPage;

			if(CustomerBeanDAO.updateCustomerFlg(id)) {
				//退会成功
				session.invalidate();
				nextPage = "customer/quitDone.jsp";
			}else {
				nextPage = "customer/quitError.jsp";
	        }
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			}
		}
	}


