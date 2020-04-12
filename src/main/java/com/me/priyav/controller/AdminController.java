package com.me.priyav.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import com.me.priyav.dao.ProductDao;
import com.me.priyav.pojo.Product;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
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
	
	@RequestMapping("/admin.htm")
	public String adminPage() {
		logger.info("Welcome to Admin Page!");
		return "admin";
	}
	
	@RequestMapping("/admin/productInventory.htm")
	public String adminProductInventory(Model model) {
		logger.info("Welcome to Admin Page!");
		List<Product> products = pDao.allProducts();
		logger.info(""+products);
		model.addAttribute("products", products);
		return "productInventory";
	}
	
	@RequestMapping("/admin/productInventory/addProduct.htm")
	public String adminAddProducts(Model model) {
		logger.info("Admin can add products here!");
		Product product = new Product();
		model.addAttribute("product", product);
		return "addProduct";
	}
	
	@RequestMapping(value="/admin/productInventory/addProduct.htm", method = RequestMethod.POST)
    public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request){

		logger.info("Admin can add products here(This is the POST method)!");
        if(result.hasErrors()){
        	logger.info("Errors are :"+result);
        	logger.info("Admin can add products here(POST method has error)!");
        	logger.info("Product items selected are: "+product.getProductName());
            logger.info("Product items selected are: "+product.getProductDescription());
            logger.info("Product items selected are: "+product.getProductCategory());
            String address= request.getParameter("productImage");
            logger.info("Photo is present at: "+address);
            return "addProduct";
        }

//        productService.addProduct(product);
        
        logger.info("Product items selected are: "+product.getProductName());
        logger.info("Product items selected are: "+product.getProductDescription());
        logger.info("Product items selected are: "+product.getProductCategory());
        int res = pDao.addProduct(product);
        
        MultipartFile productImage = product.getProductImage();
//        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        String rootDirectory = "C:\\Users\\kinja\\Desktop\\NEU things\\Web tools\\STS3_Project\\Priyav\\src\\main\\webapp\\resources\\images\\";
        logger.info("Root Directory is: "+rootDirectory);
        path = Paths.get(rootDirectory + "" + product.getProductId() + ".png");
        logger.info("Path is: "+path);
        
        if(productImage != null && !productImage.isEmpty()){
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception ex){
                ex.printStackTrace();
                throw new RuntimeException("Product image saving failed", ex);
            }
        }

        return "redirect: /priyav/admin/productInventory.htm";
    }
	
	

}
