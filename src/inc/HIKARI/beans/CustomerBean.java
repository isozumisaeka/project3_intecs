package inc.HIKARI.beans;

import java.io.Serializable;

public class CustomerBean implements Serializable{
	private static final long serialVersionUID=1L;

	//フィールド
	private int customerId;
	private String name;
	private String password;
	private String id;
	private int customerFlg;
	private String address;
	private String tel;

	//コンストラクタ
	public CustomerBean() {}
	public CustomerBean(int customerId,String name,String password,String id,int customerFlg,String address,String tel) {
		this.customerId = customerId;
		this.name = name;
		this.password = password;
		this.id = id;
		this.customerFlg = customerFlg;
		this.address = address;
		this.tel = tel;
	}
	//五十住さん追加
//	public CustomerBean(int customerId, String name, String password,String id,int customerFlg) {
//		this.customerId = customerId;
//		this.name = name;
//		this.password = password;
//		this.id = id;
//		this.customerFlg = customerFlg;
//	}
	public CustomerBean(String name, String password,String id, int customerFlg) {
		this.name = name;
		this.password = password;
		this.id = id;
		this.customerFlg = customerFlg;
	}

	//メソッド
	public int getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getId() {
		return id;
	}

	public int getCustomerFlg() {
		return customerFlg;
	}

	public String getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
