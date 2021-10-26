package inc.HIKARI.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション取得
		HttpSession session = request.getSession();
		//ページ遷移準備
		String nextPage;
		if(session != null) {
			session.invalidate();
			nextPage = "customer/logoutDone.jsp";
        } else {
            nextPage = "customer/logoutError.jsp";
        }
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
