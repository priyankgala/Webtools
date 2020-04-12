package com.me.priyav.pojo;

import javax.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class BillingAddress {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int billingAddressId;
	private String streetName;
    private String aptNo;
    private String city;
    private String state;
    private String zipCode;
    
//    @OneToOne
//    private Customer customer;

	public int getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(int billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getAptNo() {
		return aptNo;
	}

	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

//  Will decide if I need this customer mapping or not
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
    
}
