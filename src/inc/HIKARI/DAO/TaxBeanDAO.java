package inc.HIKARI.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import inc.HIKARI.beans.TaxBean;

/**
 * Servlet implementation class TaxBeanDAO
 */
public class TaxBeanDAO extends HttpServlet {

	public static List<TaxBean> getListByTax(){
		List<TaxBean> taxlist = new ArrayList<TaxBean>();
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
			//SQLの生成
			String sql = "SELECT * FROM tax ORDER BY start_tax ASC";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				int taxRate = rs.getInt("tax");
				Date startTax = rs.getDate("start_tax");

				TaxBean tax = new TaxBean(taxRate,startTax);

				taxlist.add(tax);
			}

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
		return taxlist;
	}

	public static List<TaxBean> getListByNowTax(){
		List<TaxBean> taxlist = new ArrayList<TaxBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String drivername = "com.mysql.jdbc.Driver";

			String url = "jdbc:mysql://localhost:3306/e_intecs?characterEncoding=utf-8&serverTimezone=JST";

			String username = "root";

			String password = "";

			Class.forName(drivername);

			con = DriverManager.getConnection(url,username,password);

			String sql = "SELECT * FROM tax WHERE start_tax <= CURDATE() ORDER BY start_tax DESC";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			rs.first();
			int taxRate = rs.getInt("tax");
			Date startTax = rs.getDate("start_tax");

			TaxBean tax = new TaxBean(taxRate,startTax);

			taxlist.add(tax);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
		return taxlist;
	}

	public static boolean addTax(TaxBean tax) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String drivername = "com.mysql.jdbc.Driver";

			String url = "jdbc:mysql://localhost:3306/e_intecs?characterEncoding=utf-8&serverTimezone=JST";

			String username = "root";

			String password = "";

			Class.forName(drivername);

			con = DriverManager.getConnection(url,username,password);

			String sql = "INSERT INTO `tax`(`tax`, `start_tax`) VALUES (?,?)";

			ps = con.prepareStatement(sql);
			ps.setLong(1, tax.getTax());
			ps.setDate(2, tax.getStartTax());

			int ret = ps.executeUpdate();
			if(ret != 0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}
