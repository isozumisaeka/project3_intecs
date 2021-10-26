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

public class SignupInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション取得
		HttpSession session = request.getSession();
		//まずオブジェクトをCustomerBean型で取得してから
		CustomerBean cb = (CustomerBean)session.getAttribute("customer");
		//それぞれの中身取得！
		int customerId = cb.getCustomerId();
		String name = cb.getName();
		String password = cb.getPassword();
		String id = cb.getId();
		int customerFlg = cb.getCustomerFlg();
		String address = cb.getAddress();
		String tel = cb.getTel();
		//staticメソッドを呼び出し結果を取得する
		//staticメソッドは、クラス名.メソッド名で呼び出すことができる
		List<CustomerBean> list = CustomerBeanDAO.getDcList(id);
		String nextPage;
		if (list.size()==0) {
			CustomerBean newCst = new CustomerBean(customerId,name,password,id,customerFlg,address,tel);
			//登録
			if (CustomerBeanDAO.addCustomer(newCst)) {
				//登録成功
				request.setAttribute("cst", newCst);
				nextPage = "customer/signupDone.jsp";
			}else {
				//登録失敗
				request.setAttribute("id", id);
				nextPage = "customer/signupError.jsp";
			}
		}else {
			//登録済
			request.setAttribute("id", id);
			nextPage = "customer/signupError.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}

