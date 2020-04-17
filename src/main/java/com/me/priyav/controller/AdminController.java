package com.me.priyav.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.me.priyav.dao.CartDao;
import com.me.priyav.dao.CustomerDao;
import com.me.priyav.dao.ProductDao;
import com.me.priyav.pojo.CartItem;
import com.me.priyav.pojo.Customer;
import com.me.priyav.pojo.Product;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private Path path;

	@Autowired
	ProductDao pDao;
	
	@Autowired
	CartDao cDao;

	@Autowired
	CustomerDao cstDao;
	/*
	 * 
	 * 
	 * List of Customer
	 * 
	 * 
	 * 
	 * */
	@RequestMapping("/admin/customerList/all.htm")
    public String customerList(Model model){

        List<Customer> customerList = cstDao.getAllCustomers();
        model.addAttribute("customerList", customerList);

        return "adminCustomerManagement";
    }
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Product inventory
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@RequestMapping("/admin/admin.htm")
	public String adminPage(HttpServletRequest request) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		logger.info("Welcome to Admin Page!");
		return "admin";
	}

	@RequestMapping("/admin/productInventory.htm")
	public String adminProductInventory(HttpServletRequest request, Model model) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		logger.info("Welcome to Admin Product inventory Page!");
		List<Product> products = pDao.allProducts();
		logger.info("" + products);
		model.addAttribute("products", products);
		return "productInventory";
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * Add products
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	@RequestMapping("/admin/productInventory/addProduct.htm")
	public String adminAddProducts(HttpServletRequest request, Model model) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		logger.info("Admin can add products here!");
		Product product = new Product();
		model.addAttribute("product", product);
		return "addProduct";
	}

	@RequestMapping(value = "/admin/productInventory/addProduct.htm", method = RequestMethod.POST)
	public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result,
			HttpServletRequest request) {

		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		logger.info("Admin can add products here(This is the POST method)!");
		if (result.hasErrors()) {
			logger.info("Errors are :" + result);
			logger.info("Admin can add products here(POST method has error)!");
			logger.info("Product items selected are: " + product.getProductName());
			logger.info("Product items selected are: " + product.getProductDescription());
			logger.info("Product items selected are: " + product.getProductCategory());
			String address = request.getParameter("productImage");
			logger.info("Photo is present at: " + address);
			return "addProduct";
		}

//        productService.addProduct(product);

		logger.info("Product items selected are: " + product.getProductName());
		logger.info("Product items selected are: " + product.getProductDescription());
		logger.info("Product items selected are: " + product.getProductCategory());
		int res = pDao.addProduct(product);

		MultipartFile productImage = product.getProductImage();
//        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		String rootDirectory = "C:\\Users\\kinja\\Desktop\\NEU things\\Web tools\\STS3_Project\\Priyav\\src\\main\\webapp\\resources\\images\\";
		logger.info("Root Directory is: " + rootDirectory);
		path = Paths.get(rootDirectory + "" + product.getProductId() + ".png");
		logger.info("Path is: " + path);

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(path.toString()));
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("Product image saving failed", ex);
			}
		}

		return "redirect: /priyav/admin/productInventory.htm";
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * View products
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@RequestMapping("/admin/product/viewProduct/{productId}")
	public String viewProduct(HttpServletRequest request,@PathVariable int productId, Model model) throws IOException {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		Product product = pDao.getProductById(productId);
		model.addAttribute("product", product);

		return "viewProduct";
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Delete product
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@RequestMapping("/admin/product/deleteProduct/{id}")
	public String deleteProduct(@PathVariable int id, Model model, HttpServletRequest request) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		String rootDirectory = "C:\\Users\\kinja\\Desktop\\NEU things\\Web tools\\STS3_Project\\Priyav\\src\\main\\webapp\\resources\\images\\";
		logger.info("Root Directory is: " + rootDirectory);
		path = Paths.get(rootDirectory + "" + id + ".png");
		logger.info("Path is: " + path);

		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		logger.info("Is product present in any cart Item");
		List<CartItem> itemList = cDao.getCartListByProductid();
		logger.info(""+itemList.size());
		for(int i=0;i<itemList.size();i++) {
			
			logger.info(""+itemList.get(i).getCartItemId());
		}
		
		logger.info("Remove the product from cartItem");
		int cartItemPresent= cDao.removeProdcutByProductId(id);

		int result = pDao.deleteProductById(id);
		logger.info(""+result);
		if (result == 1) {
			return "redirect:/admin/productInventory.htm";
		} else {
			return "errorPage";
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Update product
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@RequestMapping("/admin/product/updateProduct/{id}")
	public String editProduct(@PathVariable int id, Model model, HttpServletRequest request) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		Product product = pDao.getProductById(id);
		model.addAttribute("product", product);

		return "updateProduct";
	}

	@RequestMapping(value = "/admin/product/updateProduct.htm", method = RequestMethod.POST)
	public String updateProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result,
			HttpServletRequest request) {
		if (request.getAttribute("unsafe_check").equals("true")) {
			request.setAttribute("unsafe_check", "false");
			return "redirect: /priyav/login.htm";
		}
		
		if (result.hasErrors()) {
			return "updateProduct";
		}

		MultipartFile productImage = product.getProductImage();
		String rootDirectory = "C:\\Users\\kinja\\Desktop\\NEU things\\Web tools\\STS3_Project\\Priyav\\src\\main\\webapp\\resources\\images\\";
		logger.info("Root Directory is: " + rootDirectory);
		path = Paths.get(rootDirectory + "" + product.getProductId() + ".png");
		logger.info("Path is: " + path);
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(path.toString()));
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("Product image saving failed", ex);
			}
		}

		int res = pDao.updateProduct(product);
		
		return "redirect: /priyav/admin/productInventory.htm";
	}

}
