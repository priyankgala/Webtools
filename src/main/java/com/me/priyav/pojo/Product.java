package com.me.priyav.pojo;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;
import com.me.priyav.pojo.*;
import com.me.priyav.validations.CheckProductPrice;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;

	@Pattern(regexp="^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$", message="Not a correct value or you are using unsafe strings")
	@NotEmpty(message = "The product name must not be empty")
	private String productName;
	
	@NotEmpty(message = "Select a category!")
	private String productCategory;
	
	@Pattern(regexp="^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$", message="Not a correct value or you are using unsafe strings")
	@NotEmpty(message = "Write a description for the product")
	private String productDescription;
	
	@Min(value = 0, message = "The product price must not be less then zero")
	private double productPrice;

	@DecimalMin(value = "0.0", message="Enter a numeric value")
	@Min(value = 0, message = "The product unit must not be less then zero")
	private int unitInStock;

	@Transient
	private MultipartFile productImage;
	
	@OneToMany(mappedBy = "product")
    private List<CartItem> cartItemList;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getUnitInStock() {
		return unitInStock;
	}

	public void setUnitInStock(int unitInStock) {
		this.unitInStock = unitInStock;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

}
