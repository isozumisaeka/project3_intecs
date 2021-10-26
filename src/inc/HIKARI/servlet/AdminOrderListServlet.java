package inc.HIKARI.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inc.HIKARI.DAO.PurchaseBeanDAO;
import inc.HIKARI.beans.CartBean;
import inc.HIKARI.beans.CustomerBean;
import inc.HIKARI.beans.PurchaseBean;

public class AdminOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//int型のままlistに入れるとリクエスト属性に格納できないから、
		//List<PurchaseBean>型にする
		List<PurchaseBean> list = PurchaseBeanDAO.getAdminPurchasedList();
		for(PurchaseBean listitem:list) {

		}
		//取得したDBデータをリクエスト属性に格納
		request.setAttribute("list", list);
		//遷移先ぺージ準備
		String nextPage;
		if (list.size()==0) {
			nextPage = "customer/totalError.jsp";
		}
		else {
			nextPage = "admin/adminOrderList.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);











	}

}
