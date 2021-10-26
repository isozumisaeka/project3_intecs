package inc.HIKARI.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import inc.HIKARI.beans.PurchaseBean;

public class PurchaseBeanDAO extends HttpServlet {

	//購入履歴登録用1
	public static boolean addPurchase(PurchaseBean pcs) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "INSERT INTO purchase(customer_id, purchase_date, how_to_pay, price_in_tax, price_total, tax_total, postage) VALUES (?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pcs.getCustomerId());
			//現在の日付をyyyymmdd形式で取得する
			LocalDate localDate = LocalDate.now();
			//java.sql.Dateに変更
			java.sql.Date date = java.sql.Date.valueOf(localDate);
			ps.setDate(2,date);
			ps.setString(3, pcs.getHowToPay());
			ps.setInt(4, pcs.getPriceInTax());
			ps.setInt(5, pcs.getPriceTotal());
			ps.setInt(6, pcs.getTaxTotal());
			ps.setInt(7, pcs.getPostage());
			System.out.println(pcs.getCustomerId());
			System.out.println(date);
			System.out.println(pcs.getHowToPay());
			System.out.println(pcs.getPriceInTax());
			System.out.println(pcs.getPriceTotal());
			System.out.println(pcs.getTaxTotal());
			System.out.println(pcs.getPostage());
			//更新クエリの実行
			int ret = ps.executeUpdate();
			if(ret != 0) {
				return true;
			} else {
				return false;
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return false;
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				//データベースに接続されていれば切断する
				if(con != null) {
					con.close();
				}
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	//購入履歴登録用2
	public static boolean addPurchaseDetail(PurchaseBean pcs) {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT MAX(purchase_id) from purchase";

			ps1 = con.prepareStatement(sql);


			//更新クエリの実行
			rs = ps1.executeQuery();
			int pid = 0;
			//データの取得
			while(rs.next()){
			pid = rs.getInt("MAX(purchase_id)");
			System.out.println("購入番号："+pid);
			}
			//第二クエリの稼働
			String sql2 = "INSERT INTO purchase_detail(Fpurchase_id, isbn, price_solo, tax_solo, count) VALUES (?,?,?,?,?)";
			ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, pid);
			ps2.setString(2, pcs.getIsbn());
			ps2.setInt(3, pcs.getPriceSolo());
			ps2.setInt(4, pcs.getTaxSolo());
			ps2.setInt(5, pcs.getCount());
			System.out.println(pcs.getIsbn());
			System.out.println(pcs.getPriceSolo());
			System.out.println(pcs.getTaxSolo());
			System.out.println(pcs.getCount());
			//更新クエリの実行
			int ret = ps2.executeUpdate();
			if(ret != 0) {
				return true;
			}else{
				return false;
			}

		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return false;
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps1 != null) {
					ps1.close();
				}
				if(ps2 != null) {
					ps2.close();
				}
				//データベースに接続されていれば切断する
				if(con != null) {
					con.close();
				}
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	public static List<PurchaseBean> getPurchasedList(int cstId){
		//DB取得結果を格納するリスト
		List<PurchaseBean> list = new ArrayList<PurchaseBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT * FROM purchase INNER JOIN purchase_detail ON purchase.purchase_id = purchase_detail.Fpurchase_id "
					+ "INNER JOIN book ON purchase_detail.isbn=book.isbn WHERE customer_id = ? ORDER BY purchase_id DESC";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cstId);
			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				//レコード情報を取得
				int customerid = rs.getInt("customer_id");
				String purchasedate = rs.getString("purchase_date");
				int purchaseid = rs.getInt("purchase_id");
				String howtopay = rs.getString("how_to_pay");
	    	     int priceintax = rs.getInt("price_in_tax");
	    	     int pricetotal = rs.getInt("price_total");
	    	     int taxtotal = rs.getInt("tax_total");
	    	     int postage = rs.getInt("postage");
	    	     String title = rs.getString("title");
	    	     String author = rs.getString("author");
	    	     int pricesolo = rs.getInt("price_solo");
				//取得した情報を基にオブジェクトを作成
				PurchaseBean Purchase = new PurchaseBean(customerid, purchasedate, purchaseid, howtopay, priceintax, pricetotal, taxtotal, postage, title, author, pricesolo);
				//検索ヒットしたものをリストに追加
				list.add(Purchase);
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		}finally {
			try {
				if(rs !=null) {
					rs.close();
				}

				if(ps !=null) {
					ps.close();
				}

				if(con !=null) {
					con.close();
				}
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
		return list;
		}

	public static List<PurchaseBean> getAdminPurchasedList(){
		//DB取得結果を格納するリスト
		List<PurchaseBean> list = new ArrayList<PurchaseBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT * FROM purchase INNER JOIN purchase_detail ON purchase.purchase_id = purchase_detail.Fpurchase_id "
					+ "INNER JOIN book ON purchase_detail.isbn=book.isbn ORDER BY purchase_id DESC";
			ps = con.prepareStatement(sql);
			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				//レコード情報を取得
				int customerid = rs.getInt("customer_id");
				String purchasedate = rs.getString("purchase_date");
				int purchaseid = rs.getInt("purchase_id");
				String howtopay = rs.getString("how_to_pay");
	    	     int priceintax = rs.getInt("price_in_tax");
	    	     int pricetotal = rs.getInt("price_total");
	    	     int taxtotal = rs.getInt("tax_total");
	    	     int postage = rs.getInt("postage");
	    	     String title = rs.getString("title");
	    	     String author = rs.getString("author");
	    	     int pricesolo = rs.getInt("price_solo");
				//取得した情報を基にオブジェクトを作成
				PurchaseBean Purchase = new PurchaseBean(customerid, purchasedate, purchaseid, howtopay, priceintax, pricetotal, taxtotal, postage, title, author, pricesolo);
				//検索ヒットしたものをリストに追加
				list.add(Purchase);
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		}finally {
			try {
				if(rs !=null) {
					rs.close();
				}

				if(ps !=null) {
					ps.close();
				}

				if(con !=null) {
					con.close();
				}
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
		return list;
		}

	/*public static List<PurchaseBean> getPurchasedDetailList(int cstId){
		//DB取得結果を格納するリスト
		List<PurchaseBean> list = new ArrayList<PurchaseBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT * FROM purchase INNER JOIN purchase_detail ON purchase.purchase_id = purchase_detail.Fpurchase_id "
					+ "INNER JOIN book ON purchase_detail.isbn=book.isbn WHERE customer_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cstId);
			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				//レコード情報を取得
				int customerid = rs.getInt("customer_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
	    	     int pricesolo = rs.getInt("pricesolo");
	    	     int count = rs.getInt("count");

				//取得した情報を基にオブジェクトを作成
				PurchaseBean Purchase = new PurchaseBean(customerid, title, author, pricesolo, count);
				//検索ヒットしたものをリストに追加
				list.add(Purchase);
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		}finally {
			try {
				if(rs !=null) {
					rs.close();
				}

				if(ps !=null) {
					ps.close();
				}

				if(con !=null) {
					con.close();
				}
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
		return list;
		}*/
}
