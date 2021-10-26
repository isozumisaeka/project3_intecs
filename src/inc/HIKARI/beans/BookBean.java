package inc.HIKARI.beans;

import java.io.Serializable;
import java.sql.Date;

public class BookBean implements Serializable{
	//フィールド
    private static final long serialVersionUID = 1L;
    private String isbn;
    private String title;
    private String volume;
    private String publisheddate;
    private String author;
    private String publisher;
    private int price;
    private int stock;
    private String comment;
    private int deleteflg;
    private String image;
    private int volumeInt;
    private Date publishedDateDate;
    //コンストラクタ
	public BookBean() {}
    public BookBean(String isbn,
    		String title,
    		String volume,
    		String publisheddate,
    		String author,
    		String publisher,
    		int price,
    		int stock,
    		String comment,
    		int deleteflg,
    		String image
    		) {
        this.isbn = isbn;
        this.title = title;
        this.volume = volume;
        this.publisheddate = publisheddate;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.stock = stock;
        this.comment = comment;
        this.deleteflg = deleteflg;
        this.image = image;
    }
    public BookBean(String isbn,
            String title,
            int volumeInt,
            Date publishedDate,
            String author,
            String publisher,
            int price,
            int stock,
            String comment,
            int deleteflg,
            String image
            ) {
        this.isbn = isbn;
        this.title = title;
        this.volumeInt = volumeInt;
        this.publishedDateDate = publishedDate;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.stock = stock;
        this.comment = comment;
        this.deleteflg = deleteflg;
        this.image = image;
    }
    //メソッド
    public String getIsbn() {
    	return isbn;
    }
    public String getTitle() {
    	return title;
    }
    public String getVolume() {
    	if(volume.equals("0")) {
    		volume = "";
    	}else if(volume.equals("")) {
    	}else if(volume.contains("(")){
    	}else {
    		volume = "("+volume+")";
    	}
    	return volume;
    }
    public String getPublishedDate() {
    	return publisheddate;
    }
    public String getAuthor() {
    	return author;
    }
    public String getPublisher() {
    	return publisher;
    }
    public int getPrice() {
    	return price;
    }
    public int getStock() {
    	return stock;
    }
    public String getComment() {
    	return comment;
    }
    public int getDeleteFlg() {
    	return deleteflg;
    }
    public String getImage() {
    	return image;
    }
    public int getVolumeInt() {
        return volumeInt;
    }
    public Date getPublishedDateDate() {
        return publishedDateDate;
    }
}
