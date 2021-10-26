package inc.HIKARI.beans;

import java.sql.Date;

import javax.servlet.http.HttpServlet;

public class TaxBean extends HttpServlet {
	//フィールド
	private static final long serialVersionUID = 1L;
	private int tax;
	private Date startTax;
	//コンストラクタ
    public TaxBean() {}
    public TaxBean(int tax,
    		Date startTax
    		) {
    	this.tax = tax;
    	this.startTax = startTax;
    }
    //メソッド
    public int getTax() {
    	return tax;
    }
    public Date getStartTax() {
    	return startTax;
    }
    public double getTaxRate() {
    	return tax * 0.01;
    }

}
