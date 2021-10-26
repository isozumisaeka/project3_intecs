package inc.HIKARI.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import inc.HIKARI.beans.BookBean;

public class BookBeanDAO extends HttpServlet {

	public static List<BookBean> getListByBook(){
		//DB取得結果を格納するリスト
		List<BookBean> list = new ArrayList<BookBean>();

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
			String sql = "select * from book";
			ps = con.prepareStatement(sql);

			//クエリの実行
			rs = ps.executeQuery();


			while(rs.next()) {
				//レコード情報を取得
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Integer intvolume = rs.getInt("volume");
				String volume = intvolume.toString();
				Date publisheddate = rs.getDate("published_date");
				SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy年MM月dd日");
				String date = dtfmt.format(publisheddate);
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String comment = rs.getString("comment");
				int deleteflg = rs.getInt("delete_flg");
				String image = rs.getString("image");

				//取得した情報を基にオブジェクトを作成
				BookBean book = new BookBean(isbn, title, volume, date, author, publisher, price, stock, comment, deleteflg, image);
				//検索ヒットしたものをリストに追加
				list.add(book);
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
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
			}
		}
		return list;
		}
	//ランダムで作品をピックアップ
	public static List<BookBean> getRandomList(){
		List<BookBean> randomlist = new ArrayList<BookBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//sqlの作成
			String sql ="SELECT * FROM book ORDER BY RAND()";
			ps = con.prepareStatement(sql);




			rs = ps.executeQuery();

			while(rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Integer intvolume = rs.getInt("volume");
				String volume = intvolume.toString();
				Date publisheddate = rs.getDate("published_date");
				SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy年MM月dd日");
				String date = dtfmt.format(publisheddate);
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String comment = rs.getString("comment");
				int deleteflg = rs.getInt("delete_flg");
				String image = rs.getString("image");
				System.out.println(date);
				System.out.println(date instanceof String);
				BookBean rndbook = new BookBean(isbn, title, volume, date, author, publisher, price, stock, comment, deleteflg, image);

				randomlist.add(rndbook);
				if(randomlist.size() == 14) {
					break;
				}
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return null;
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

		return randomlist;
	}
	//新作をピックアップ
	public static List<BookBean> getNewList(){
		List<BookBean> newlist = new ArrayList<BookBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//sqlの作成
			String sql ="SELECT * FROM book ORDER BY published_date DESC";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Integer intvolume = rs.getInt("volume");
				String volume = intvolume.toString();
				Date publisheddate = rs.getDate("published_date");
				SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy年MM月dd日");
				String date = dtfmt.format(publisheddate);
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String comment = rs.getString("comment");
				int deleteflg = rs.getInt("delete_flg");
				String image = rs.getString("image");
				System.out.println(date);
				System.out.println(date instanceof String);
				BookBean nbook = new BookBean(isbn, title, volume, date, author, publisher, price, stock, comment, deleteflg, image);

				newlist.add(nbook);
				if(newlist.size() == 14) {
					break;
				}
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return null;
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

		return newlist;
}

	//ランキングをピックアップ
	public static List<BookBean> getRankingList(){
		List<BookBean> rankinglist = new ArrayList<BookBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//sqlの作成
			String sql ="SELECT * FROM book INNER JOIN purchase_detail on book.isbn = purchase_detail.isbn group by purchase_detail.isbn ORDER BY book.isbn DESC";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Integer intvolume = rs.getInt("volume");
				String volume = intvolume.toString();
				Date publisheddate = rs.getDate("published_date");
				SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy年MM月dd日");
				String date = dtfmt.format(publisheddate);
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String comment = rs.getString("comment");
				int deleteflg = rs.getInt("delete_flg");
				String image = rs.getString("image");
				System.out.println(date);
				System.out.println(date instanceof String);
				BookBean rbook = new BookBean(isbn, title, volume, date, author, publisher, price, stock, comment, deleteflg, image);

				rankinglist.add(rbook);
				if(rankinglist.size() == 14) {
					break;
				}
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return null;
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
				ex.printStackTrace();
			}
		}

		return rankinglist;
}
	//商品詳細を取得する
	public static List<BookBean> getBookDetail(String bookisbn){
		List<BookBean> detail = new ArrayList<BookBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/e_intecs");
			//コネクションの取得
			con = ds.getConnection();
			//sqlの作成
			String sql ="SELECT * FROM book WHERE isbn = ?";
			ps = con.prepareStatement(sql);


			ps.setString(1, bookisbn);

			rs = ps.executeQuery();

			while(rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Integer intvolume = rs.getInt("volume");
				String volume = intvolume.toString();
				Date publisheddate = rs.getDate("published_date");
				SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy年MM月dd日");
				String date = dtfmt.format(publisheddate);
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String comment = rs.getString("comment");
				int deleteflg = rs.getInt("delete_flg");
				String image = rs.getString("image");
				System.out.println(date instanceof String);
				BookBean dbook = new BookBean(isbn, title, volume, date, author, publisher, price, stock, comment, deleteflg, image);

				detail.add(dbook);
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return null;
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

		return detail;
	}
	public static List<BookBean> getBookList(String keyword){
		//DB取得結果を格納するリスト
		List<BookBean> list = new ArrayList<BookBean>();

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
			String sql = "select * from book where title like ? OR author like ? OR author like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			ps.setString(2, "%"+keyword+"%");
			ps.setString(3, "%"+keyword+"%");

			//クエリの実行
			rs = ps.executeQuery();


			while(rs.next()) {
				//レコード情報を取得
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Integer intvolume = rs.getInt("volume");
				String volume = intvolume.toString();
				Date publisheddate = rs.getDate("published_date");
				SimpleDateFormat dtfmt = new SimpleDateFormat("yyyy年MM月dd日");
				String date = dtfmt.format(publisheddate);
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String comment = rs.getString("comment");
				int deleteflg = rs.getInt("delete_flg");
				String image = rs.getString("image");

				//取得した情報を基にオブジェクトを作成
				BookBean book = new BookBean(isbn, title, volume, date, author, publisher, price, stock, comment, deleteflg, image);
				//検索ヒットしたものをリストに追加
				list.add(book);
			}
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
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
			}
		}
		return list;
		}

	//在庫数を取得するメソッド
	public static int getBookStock(String isbn) {
		int stock=-1;

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
			String sql = "SELECT stock FROM book WHERE isbn=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, isbn);

			//クエリの実行
			rs = ps.executeQuery();

			//データの取得
			while(rs.next()) {
				stock = rs.getInt("stock");
			}

		}catch(Exception ex) {
			System.err.println(ex.getMessage());
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
			}
		}
		return stock;
	}
	public static boolean addBook(BookBean book) {

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
			String sql = "INSERT INTO `book`(`isbn`,`title`, `volume`, `published_date`, `author`, `publisher`, `price`, `stock`, `comment`, `delete_flg`, `image`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setInt(3, book.getVolumeInt());
			ps.setDate(4, book.getPublishedDateDate());
			ps.setString(5, book.getAuthor());
			ps.setString(6, book.getPublisher());
			ps.setInt(7, book.getPrice());
			ps.setInt(8, book.getStock());
			ps.setString(9, book.getComment());
			ps.setInt(10, book.getDeleteFlg());
			ps.setString(11, book.getImage());

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

	public static boolean updateStock(String isbn, int stock) {
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
			String sql = "UPDATE `book` SET `stock`=? WHERE `isbn`=?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, stock);
			ps.setString(2, isbn);

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
