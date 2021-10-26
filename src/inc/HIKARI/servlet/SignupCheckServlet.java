package inc.HIKARI.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inc.HIKARI.beans.CustomerBean;

public class SignupCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		//セッションの取得
		HttpSession session = request.getSession();
		//カスタマーオブジェクトを生成
		CustomerBean cb = new CustomerBean();
		cb.setName(name);
		cb.setAddress(address);
		cb.setTel(tel);
		cb.setId(id);
		cb.setPassword(password);
		//カスタマーオブジェクトをセッション属性にセット
		session.setAttribute("customer", cb);
		RequestDispatcher rd = request.getRequestDispatcher("customer/signupCheck.jsp");
		rd.forward(request, response);
	}

}
