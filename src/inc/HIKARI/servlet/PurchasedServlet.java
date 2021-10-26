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
import inc.HIKARI.DAO.TaxBeanDAO;
import inc.HIKARI.beans.CartBean;
import inc.HIKARI.beans.CustomerBean;
import inc.HIKARI.beans.PurchaseBean;
import inc.HIKARI.beans.TaxBean;

/**
 * Servlet implementation class PurchasedServlet
 */
public class PurchasedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション取得
		HttpSession session = request.getSession();
		//各種格納用変数を定義
		int customerId = 0;
		int priceintax = 0;
		int pricetotal = 0;
		int taxtotal = 0;
		int tax = 0;
		int postage = 500;
		String isbn = null;
		int pricesolo = 0;
		String howtopay = request.getParameter("howtopay");
		int taxsolo = 0;
		int count = 0;
		String nextPage;
		//オブジェクトを取得
		List<CartBean> cabs = (List<CartBean>)session.getAttribute("cart");
		List<CustomerBean> cubs = (List<CustomerBean>)session.getAttribute("login");
		List<TaxBean> taxlist = TaxBeanDAO.getListByTax();
		//税を取得
		for(TaxBean atax:taxlist) {
		tax = atax.getTax();
		}


		//ユーザーidの取得
		for(CustomerBean cub:cubs) {
		customerId = cub.getCustomerId();
		}
		//カート情報からpurchaseに送るデータを取得
		for(CartBean cab:cabs) {
			count = cab.getCount();
			priceintax = priceintax + (cab.getPrice() + cab.getPrice()*tax/100)* count;
			pricetotal = pricetotal + cab.getPrice() * count;
			taxtotal = taxtotal + cab.getPrice()*tax/100 * count;

			if(priceintax >= 5000) {
				postage = 0;
		}
		}
		//purchaseに登録
		PurchaseBean newPcs = new PurchaseBean(customerId ,howtopay,priceintax,pricetotal,taxtotal,postage);
		System.out.println(newPcs.getCustomerId());
		System.out.println(newPcs.getHowToPay());
		System.out.println(newPcs.getPriceInTax());
		System.out.println(newPcs.getPriceTotal());
		System.out.println(newPcs.getTaxTotal());
		System.out.println(newPcs.getPostage());
		//登録
		if (PurchaseBeanDAO.addPurchase(newPcs)) {
			//登録成功

			nextPage =  "/customer/purchase.jsp";
		}else {
			//登録失敗
			nextPage =  "IndexServlet";
		}



		//purchase_detailに登録
		for(CartBean cabd:cabs) {
			isbn = cabd.getIsbn();
			pricesolo = cabd.getPrice();
			taxsolo = cabd.getPrice()*tax/100;
			count = cabd.getCount();
			//登録
			//purchase_detailテーブルに送る箱を用意
		PurchaseBean newPcsd = new PurchaseBean(isbn ,pricesolo,taxsolo,count);
		System.out.println(newPcsd.getIsbn());
		System.out.println(newPcsd.getPriceSolo());
		System.out.println(newPcsd.getTaxSolo());
		System.out.println(newPcsd.getCount());
		if (PurchaseBeanDAO.addPurchaseDetail(newPcsd)) {
			//登録成功

			nextPage =  "/customer/purchase.jsp";
		}else {
			//登録失敗
			nextPage =  "IndexServlet";
		}





			}
			//登録
		request.setAttribute("test", pricetotal);
		session.removeAttribute("cart");
		nextPage = "/customer/purchase.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}


	}


