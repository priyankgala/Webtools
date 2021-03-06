package com.me.priyav.pojo;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class CustOrder {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerOrderId;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "custId")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "billingAddressId")
    private BillingAddress billingAddress;

	public int getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(int customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
    
    
}
