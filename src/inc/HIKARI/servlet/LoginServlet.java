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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを受け取る
		request.setCharacterEncoding("UTF-8");
		String inputId = request.getParameter("id");
		String inputPw = request.getParameter("password");


		//staticメソッドを呼び出し結果を取得
		//staticメソッドは、クラス名.メソッド名で呼び出す
		List<CustomerBean> list = CustomerBeanDAO.getCustomerList(inputId, inputPw);


		//ログイン成功orログイン失敗ページにフォワードする
		String nextPage;

		if (list.size()==0) {
			//ログイン失敗
			nextPage = "customer/loginError.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);

		}
			//いそずみ 追記
			for(CustomerBean cst: list) {
				int cstFlg = cst.getCustomerFlg();

			if(cstFlg == 2) {
				//アドミンログイン成功
				//取得したDBデータをセッション属性に格納
				HttpSession session = request.getSession();
				session.setAttribute("login", list);
				nextPage ="admin/adminIndex.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(nextPage);
				rd.forward(request, response);

			}else if(cstFlg== 1) {
				//カスタマーログイン成功
				//取得したDBデータをセッション属性に格納
				HttpSession session = request.getSession();
				session.setAttribute("login", list);
				nextPage ="customer/loginSuccess.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(nextPage);
				rd.forward(request, response);
			}
			}

			}
	}



