package inc.HIKARI.beans;

import java.io.Serializable;

public class PurchaseBean implements Serializable{
	//フィールド
	private static final long serialVersionUID = 1L;
	private int customerid;
	private String purchasedate;
	private int purchaseid;
	private String howtopay;
	private int priceintax;
	private int pricetotal;
	private int taxtotal;
	private int postage;
	private String isbn;
	private int pricesolo;
	private int taxsolo;
	private int count;
	private String title;
	private String author;
    //コンストラクタ
	public PurchaseBean() {}
	//全体参照用
    public PurchaseBean(
     int customerid,
     String purchasedate,
     String howtopay,
     int priceintax,
     int pricetotal,
     int taxtotal,
     int postage,
     String isbn,
     int pricesolo,
     int taxsolo,
     int count
    		) {
        this.customerid = customerid;
        this.purchasedate = purchasedate;
        this.howtopay = howtopay;
        this.priceintax = priceintax;
        this.pricetotal = pricetotal;
        this.taxtotal = taxtotal;
        this.postage = postage;
        this.isbn = isbn;
        this.pricesolo = pricesolo;
        this.taxsolo = taxsolo;
        this.count = count;
    }
    //purchaseテーブルに送る用
    public PurchaseBean(
    	     int customerid,
    	     String howtopay,
    	     int priceintax,
    	     int pricetotal,
    	     int taxtotal,
    	     int postage
    	    		) {
    	        this.customerid = customerid;
    	        this.howtopay = howtopay;
    	        this.priceintax = priceintax;
    	        this.pricetotal = pricetotal;
    	        this.taxtotal = taxtotal;
    	        this.postage = postage;
    	    }
    //purchase_detailテーブルに送る用
    public PurchaseBean(
    	     String isbn,
    	     int pricesolo,
    	     int taxsolo,
    	     int count
    	    		) {

    	        this.isbn = isbn;
    	        this.pricesolo = pricesolo;
    	        this.taxsolo = taxsolo;
    	        this.count = count;
    	    }
    //購入リスト用
    public PurchaseBean(
    	     int customerid,
    	     String purchasedate,
    	     int purchaseid,
    	     String howtopay,
    	     int priceintax,
    	     int pricetotal,
    	     int taxtotal,
    	     int postage,
    	     String title,
    	     String author,
    	     int pricesolo

    	    		) {
    	        this.customerid = customerid;
    	        this.purchasedate = purchasedate;
    	        this.setPurchaseid(purchaseid);
    	        this.howtopay = howtopay;
    	        this.priceintax = priceintax;
    	        this.pricetotal = pricetotal;
    	        this.taxtotal = taxtotal;
    	        this.postage = postage;
    	        this.title = title;
    	        this.author = author;
    	        this.pricesolo = pricesolo;
    	    }
    //購入履歴詳細用
   /* public PurchaseBean(
    		int customerid,
    		String title,
    		String author,
    		int pricesolo,
    		int count
    				) {
    			this.customerid = customerid;
    			this.title = title;
    			this.author = author;
    			this.pricesolo = pricesolo;
    			this.count = count;
    }*/

    //メソッド
    public int getCustomerId() {
    	return customerid;
    }
    public void setCustomerId(int customerid) {
    	this.customerid = customerid;
    }
    public String getPurchaseDate() {

    	return purchasedate;
    }
    public String getHowToPay() {
    	return howtopay;
    }
    public int getPriceInTax() {
    	return priceintax;
    }
    public int getPriceTotal() {
    	return pricetotal;
    }
    public int getTaxTotal() {
    	return taxtotal;
    }
    public int getPostage() {
    	return postage;
    }
    public String getIsbn() {
    	return isbn;
    }
    public int getPriceSolo() {

    	return pricesolo;
    }
    public int getTaxSolo() {
    	return taxsolo;
    }
    public int getCount() {
    	return count;
    }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPurchaseId() {
		return purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}
}
