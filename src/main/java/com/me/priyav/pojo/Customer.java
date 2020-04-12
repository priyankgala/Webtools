package com.me.priyav.pojo;

import javax.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Customer {

	public Customer() {

	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int custId;

	private String custFName;
	private String custLName;
// Email to be used a username to login
	private String custEmail;
	private String custPhone;
	private String custPassword;
//	private String confCustPassword;
	
	@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "billingAddressId")
    private BillingAddress billingAddress;
	
//	@OneToOne
//    @JoinColumn(name = "cartId")
//    private ShoppingCart cart;
	
	//getter and setters

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustFName() {
		return custFName;
	}

	public void setCustFName(String custFName) {
		this.custFName = custFName;
	}

	public String getCustLName() {
		return custLName;
	}

	public void setCustLName(String custLName) {
		this.custLName = custLName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

//	public ShoppingCart getCart() {
//		return cart;
//	}
//
//	public void setCart(ShoppingCart cart) {
//		this.cart = cart;
//	}
//	

}
