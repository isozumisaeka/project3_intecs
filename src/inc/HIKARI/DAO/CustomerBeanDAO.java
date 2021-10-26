package inc.HIKARI.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import inc.HIKARI.beans.CustomerBean;

public class CustomerBeanDAO {

	//フィールド--------------------
	//コンストラクタ---------------
	//メソッド----------------------

	//顧客情報の重複をチェックする
	public static List<CustomerBean> getDcList(String mail){
		//DB取得結果を格納するリスト
		List<CustomerBean> list = new ArrayList<CustomerBean>();
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
			String sql = "SELECT * FROM customer WHERE email = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mail);
			//クエリの実行
			rs = ps.executeQuery();
			//データの取得
			while(rs.next()) {
				//1レコードの情報を取得
				int customerId = rs.getInt("customer_id");
				String name = rs.getString("customer_name");
				String password = rs.getString("password");
				String id = rs.getString("email");
				int customerFlg = rs.getInt("customer_flg");
				String address = rs.getString("address");
				String tel = rs.getString("phone_number");
				//取得したデータを基に単語オブジェクトを作成
				CustomerBean cst = new CustomerBean(customerId,name,password,id,customerFlg,address,tel);
				//リストに追加
				list.add(cst);
			}
		}catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
			}
			//データベースに接続されていれば切断する
			if(con != null) {
				con.close();
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	return list;
}

	//顧客登録用
	public static boolean addCustomer(CustomerBean cst) {
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
			String sql = "INSERT INTO customer(customer_name, password, email, customer_flg, login_flg, address, phone_number) VALUES (?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, cst.getName());
			ps.setString(2, cst.getPassword());
			ps.setString(3, cst.getId());
			ps.setString(4, "1");
			ps.setString(5, "0");
			ps.setString(6, cst.getAddress());
			ps.setString(7, cst.getTel());

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

	//ログイン用
	public static List<CustomerBean> getCustomerList(String inputId, String inputPw){
		//DB取得結果を格納するリスト
		List<CustomerBean> list = new ArrayList<CustomerBean>();
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

			//SQL文の生成
			String sql = "SELECT * FROM customer WHERE email=? AND password=? AND NOT customer_flg='0'";
			ps = con.prepareStatement(sql);

			ps.setString(1, inputId);
			ps.setString(2, inputPw);

			//クエリ実行
			rs = ps.executeQuery();

			//データ取得
			//rs.getString("カラム名")でカラム内のデータを取得できる
			while(rs.next()) {
				int customerId = rs.getInt("customer_id");
				String name = rs.getString("customer_name");
				String password = rs.getString("password");
				String id = rs.getString("email");
				int customerFlg = rs.getInt("customer_flg");
				String address = rs.getString("address");
				String tel = rs.getString("phone_number");

				//オブジェクト生成
				CustomerBean login = new CustomerBean(customerId,name,password,id,customerFlg,address,tel);

				//リストに追加
				list.add(login);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		finally {

			try {
				if(rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		return list;
	}

	//退会用のサーブレット
	public static boolean updateCustomerFlg(String mail) {
		//DB取得結果を格納するリスト
		//List<CustomerBean> list = new ArrayList<CustomerBean>();
		Connection con = null;
		PreparedStatement ps = null;
		//ResultSet rs = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "UPDATE customer set customer_flg=0 where email=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mail);

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

	//会員情報更新メソッド
	public static boolean updateCustomerInfo(String name, String address, String tel, String id, String password,int customerId) {
		Connection con = null;
		PreparedStatement ps = null;
		//ResultSet rs = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "UPDATE customer SET customer_name=?, address=?, phone_number=?, email=?, password=? WHERE customer_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, tel);
			ps.setString(4, id);
			ps.setString(5, password);
			ps.setInt(6, customerId);

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

	//管理者ユーザー更新ページの初期表示用メソッド
	public static List<CustomerBean> getAdminUserList() {
		//DB取得結果を格納するリスト
		List<CustomerBean> list = new ArrayList<CustomerBean>();

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
			String sql = "SELECT * FROM customer WHERE customer_flg=2;";
			ps = con.prepareStatement(sql);
			//更新クエリの実行
			rs = ps.executeQuery();

			//データの取得
			while(rs.next()) {
				//1レコードの情報を取得
				int customerId = rs.getInt("customer_id");
				String name = rs.getString("customer_name");
				String password = rs.getString("password");
				String id = rs.getString("email");
				int customerFlg = rs.getInt("customer_flg");
				String address = rs.getString("address");
				String tel = rs.getString("phone_number");
				//取得したデータを基にオブジェクトを作成
				CustomerBean cst = new CustomerBean(customerId,name,password,id,customerFlg,address,tel);
				//リストに追加
				list.add(cst);
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
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
		return list;
	}
	//全ユーザー一覧を表示する（五十住）
	public static List<CustomerBean> getAllUsers(){
		//DB取得結果を格納するリスト
		List<CustomerBean> list = new ArrayList<CustomerBean>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			//コンテキストの取得
			Context context = new InitialContext();

			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");

			//コネクションの取得
			con = ds.getConnection();

			//statementオブジェクトの生成
			st = con.createStatement();
			//SQL文の生成
			String sql = "SELECT * FROM customer";

			//クエリ実行
			rs = st.executeQuery(sql);

			//データ取得
			//rs.getString("カラム名")でカラム内のデータを取得できる
			while(rs.next()) {
				int customerId = rs.getInt("customer_id");
				String name = rs.getString("customer_name");
				String password = rs.getString("password");
				String id = rs.getString("email");
				int customerFlg = rs.getInt("customer_flg");
				String address = rs.getString("address");
				String tel = rs.getString("phone_number");

				//オブジェクト生成
				CustomerBean cb = new CustomerBean(customerId,name,password,id,customerFlg,address,tel);

				//リストに追加
				list.add(cb);
			}

			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		finally {

			try {
				if(rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		return list;
	}
//アドミンユーザーを登録する（五十住）
	//顧客登録用
	public static boolean addAdminUser(CustomerBean cst) {
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
			String sql = "INSERT INTO customer(customer_name,password, email, customer_flg) VALUES (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, cst.getName());
			ps.setString(2, cst.getPassword());
			ps.setString(3, cst.getId());
			ps.setString(4, "2");


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
}

