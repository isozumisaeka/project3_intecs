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
 * Servlet implementation class UserInfoUpdateServelt
 */
public class UserInfoUpdateServelt extends HttpServlet {private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを受け取る
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		//入力がない場合はnullに変換
		if (address.equals("")) {
			address = null;
		}
		if (tel.equals("")) {
			tel = null;
		}

		//ログイン中ユーザーの顧客番号を取得
		//初期値にありえない値を設定
		int customerId=-1;
		HttpSession session = request.getSession();
		List<CustomerBean> login =(List<CustomerBean>)session.getAttribute("login");
		for(CustomerBean cb : login) {
			customerId = cb.getCustomerId();
			break;
		}

		//DAO
		//ログイン中ユーザーの登録情報を更新
		boolean flg = CustomerBeanDAO.updateCustomerInfo(name, address, tel, id, password, customerId);

		if (flg) {
			//登録情報の変更完了
			//変更内容をloginセッションに反映
			for(CustomerBean cb : login) {
				cb.setName(name);
				cb.setAddress(address);
				cb.setTel(tel);
				cb.setId(id);
				cb.setPassword(password);
				break;
			}
			response.sendRedirect("customer/userInfoEditDone.jsp");
		}else {
			//登録情報の変更失敗
			response.sendRedirect("customer/userInfoEditError.jsp");
		}
	}
}