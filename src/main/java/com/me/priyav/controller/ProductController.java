package com.me.priyav.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.me.priyav.dao.ProductDao;
import com.me.priyav.pojo.Product;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	ProductDao pDao;
	
	/*
	 * 
	 * All product List for Guest(Usertype == null)
	 * 
	 * */
	@RequestMapping("/product/all")
    public String getProducts(Model model){
        logger.info("Inside my getProducts method");
        List<Product> products = pDao.allProducts();
		logger.info("" + products);
		model.addAttribute("products", products);
		
		return "productInventoryGuestCustomer";
    }
	
	/*
	 * 
	 * All product List for Usertype == null
	 * 
	 * */
	@RequestMapping("/customer/product/all")
    public String getProductsForCustomer(HttpServletRequest request, Model model){
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
        logger.info("Inside my getProducts method");
        List<Product> products = pDao.allProducts();
		logger.info("" + products);
		model.addAttribute("products", products);
		
		return "productInventoryCustomer";
    }
	
	/*
	 * 
	 * View product for Guest Customer
	 * 
	 * */
	@RequestMapping("/product/viewProduct/{productId}")
    public String viewProduct(@PathVariable int productId, Model model) throws IOException{
        Product product = pDao.getProductById(productId);
        model.addAttribute("product", product);
        logger.info("return customer view for the product");
        return "viewProduct";
    }

	
	/*
	 * 
	 * View product for  Customer
	 * 
	 * */
	@RequestMapping("/customer/product/viewProduct/{productId}")
    public String viewProductForCustomer(@PathVariable int productId, Model model, HttpServletRequest request) throws IOException{
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
        Product product = pDao.getProductById(productId);
        model.addAttribute("product", product);
        logger.info("return customer view for the product");
        return "viewProductCustomer";
    }
	
	
	/*
	 * 
	 * See products by Category
	 * 
	 * */
    @RequestMapping("/customer/product/productListByCategory")
    public String getProductByCategory(@RequestParam("searchCondition") String searchType, Model model, HttpServletRequest request){
    	if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
    	List<Product> products = pDao.getProductByCategory(searchType);
        model.addAttribute("products", products);
        model.addAttribute("searchCondition", searchType);

        return "productList";
    }

}
