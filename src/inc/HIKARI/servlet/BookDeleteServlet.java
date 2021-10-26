package inc.HIKARI.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inc.HIKARI.beans.CartBean;

public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログイン判定
		HttpSession session = request.getSession();
		if (session.getAttribute("login")==null) {
			//未ログインの場合はloginAnnounce.jspに遷移
			response.sendRedirect("/intecs/customer/loginAnnounce.jsp");

		}else {
			//すべてクリアが押された場合
			String value = request.getParameter("delete");
			if (value.equals("すべてクリア")) {
				//削除成功のメッセージ用意
				String message = "カートから削除しました。";
				session.setAttribute("deleteMessage", message);
				session.removeAttribute("cart");
				response.sendRedirect("customer/cartNull.jsp");
			}else {

				try {
					//ログイン済の場合はカート内から削除処理
					//cartセッションからリストを取得
					List<CartBean> list = (List<CartBean>)session.getAttribute("cart");

					//対象を削除
					int cartNumber = Integer.parseInt(request.getParameter("cartNumber"));
					list.remove(cartNumber);

					//削除成功のメッセージ用意
					String message = "カートから削除しました。";
					session.setAttribute("deleteMessage", message);

					//cart.jspに戻る or カート内に商品がない場合はcartNull.jspへ遷移
					String nextPage;
					if (list.size()==0) {
						nextPage = "customer/cartNull.jsp";
					}else {
						nextPage = "customer/cart.jsp";
					}

					//フォワードだとリロード時にエラーになるのでリロード対策でリダイレクト
					response.sendRedirect(nextPage);

				} catch (Exception e) {
					response.sendRedirect("customer/totalError.jsp");
				}
			}
		}
	}
}
