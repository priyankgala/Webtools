package com.me.priyav.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.me.priyav.dao.ProductDao;
import com.me.priyav.pojo.Product;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	ProductDao pDao;
	
	/*
	 * 
	 * All product List for Customers
	 * 
	 * */
	@RequestMapping("/product/all")
    public String getProducts(Model model){
        logger.info("Inside my getProducts method");
        List<Product> products = pDao.allProducts();
		logger.info("" + products);
		model.addAttribute("products", products);
		
		return "productInventoryCustomer";
    }
	
	/*
	 * 
	 * View product for customer
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
	 * See products by Category
	 * 
	 * */
//    @RequestMapping("/product/productListByCategory")
//    public String getProductByCategory(@RequestParam("searchCondition") String searchCondition, Model model){
//        List<Product> products = pDao.getProductByCategory();
//        model.addAttribute("products", products);
//        model.addAttribute("searchCondition", searchCondition);
//
//        return "productList";
//    }

}
