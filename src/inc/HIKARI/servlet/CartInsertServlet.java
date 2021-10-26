package inc.HIKARI.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inc.HIKARI.beans.CartBean;

public class CartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン判定
		HttpSession session = request.getSession();
		if (session.getAttribute("login")==null) {
			//ログインしてなかったらloginAnnounce.jspへ遷移
			response.sendRedirect("/intecs/customer/loginAnnounce.jsp");
		}else {
			//ログイン済の場合は追加処理
			//リクエストで値を受け取る
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String volume = request.getParameter("volume");
			String publishedDate = request.getParameter("publishedDate");
			String author = request.getParameter("author");
			String publisher = request.getParameter("publisher");
			int price = Integer.parseInt(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			String comment = request.getParameter("comment");
			int deleteFlg = Integer.parseInt(request.getParameter("deleteFlg"));
			String image = request.getParameter("image");
			int count = Integer.parseInt(request.getParameter("count"));

			//cartセッションが存在しているか確認
			if (session.getAttribute("cart")!=null) {
				//存在していれば、cartセッションからリスト取得→オブジェクト生成→リストに追加→setAttributeする
				//まずはcartセッションからリスト取得
				List<CartBean> list = (List<CartBean>)session.getAttribute("cart");

				//同じisbnがカート内に存在していれば、カウントに追加する
				//カートに追加した書籍のisbnと、カートに入っている書籍のisbnをひとつずつ比較する
				boolean isbnFlg=true;
				for (CartBean cb : list) {
					//isbnが一致していれば、カート内のカウントに取得したカウントを足す
					if (isbn.equals(cb.getIsbn())) {
						int countUp = cb.getCount() + count;
						cb.setCount(countUp);

						//追加成功のメッセージ用意
						String message = "カートに追加しました。";
						session.setAttribute("insertMessage", message);

						//フォワードだとリロード時にエラーになるのでリロード対策でリダイレクト
						response.sendRedirect("customer/cart.jsp");

						isbnFlg=false;
						break;
					}
				}

				if (isbnFlg) {
					//カートに同じisbnの書籍が無かった場合は、普通に追加する
					//オブジェクト生成
					CartBean cart = new CartBean(isbn,title,volume,publishedDate,author,publisher,price,stock,comment,deleteFlg,image,count);

					//リストに追加しcartセッションにセット
					list.add(cart);
					session.setAttribute("cart", list);

					//追加成功のメッセージ用意
					String message = "カートに追加しました。";
					session.setAttribute("insertMessage", message);

					//フォワードだとリロード時にエラーになるのでリロード対策でリダイレクト
					response.sendRedirect("customer/cart.jsp");
				}

			}else {
				//cartセッションが存在していなければ、新規でlist作成しオブジェクト生成→リストに追加→setAttribute
				List<CartBean> list = new ArrayList<CartBean>();
				CartBean cart = new CartBean(isbn,title,volume,publishedDate,author,publisher,price,stock,comment,deleteFlg,image,count);
				list.add(cart);
				session.setAttribute("cart", list);

				//追加成功のメッセージ用意
				String message = "カートに追加しました。";
				session.setAttribute("insertMessage", message);

				//フォワードだとリロード時にエラーになるのでリロード対策でリダイレクト
				response.sendRedirect("customer/cart.jsp");
			}
		}
	}
}
