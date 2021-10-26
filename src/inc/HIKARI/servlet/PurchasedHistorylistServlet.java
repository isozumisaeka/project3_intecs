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
import inc.HIKARI.beans.CustomerBean;
import inc.HIKARI.beans.PurchaseBean;

public class PurchasedHistorylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションの取得
		HttpSession session = request.getSession();
		//オブジェクトをPurchaseBean型で取得
		List<CustomerBean> pb = (List<CustomerBean>)session.getAttribute("login");
		int id = 0;
		for(CustomerBean pbitem : pb) {
		id = pbitem.getCustomerId();
		}
		System.out.println(id);
		//int型のままlistに入れるとリクエスト属性に格納できないから、
		//List<PurchaseBean>型にする
		List<PurchaseBean> list = PurchaseBeanDAO.getPurchasedList(id);
		for(PurchaseBean listitem:list) {
		System.out.println(listitem.getCustomerId());
		}
		//取得したDBデータをリクエスト属性に格納
		request.setAttribute("list", list);
		//遷移先ぺージ準備
		String nextPage;
		if (list.size()==0) {
			nextPage = "customer/totalError.jsp";
		}
		else {
			nextPage = "customer/purchasedHistory.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);











	}

}
