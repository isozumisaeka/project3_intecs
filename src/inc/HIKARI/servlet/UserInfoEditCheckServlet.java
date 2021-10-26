package inc.HIKARI.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserInfoEditCheckServlet
 */
public class UserInfoEditCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを受け取る
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		//名前、メールアドレス、パスワードのいずれかが空欄の時は入力画面に戻す
		if (name.equals("") || id.equals("") || password.equals("")) {
			HttpSession session = request.getSession();
			String message = "入力必須項目(名前、メールアドレス、パスワード)が入力されていません。";
			session.setAttribute("message", message);
			response.sendRedirect("customer/userInfoEdit.jsp");
		}else {
			//必須項目が入力されている場合は確認画面へ進む
			//入力されている値をリクエスト属性に格納して確認画面へ渡す
			request.setAttribute("name", name);
			request.setAttribute("address", address);
			request.setAttribute("tel", tel);
			request.setAttribute("id", id);
			request.setAttribute("password", password);
			RequestDispatcher rd = request.getRequestDispatcher("customer/userInfoEditCheck.jsp");
			rd.forward(request, response);
		}
	}
}
