package com.me.priyav.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.mindrot.jbcrypt.BCrypt;

import com.me.priyav.pojo.Product;

public class ProductDao extends Dao{

	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			beginTransaction();
			getSession().save(product);
			commit();
			result = 1;
		} catch (HibernateException e) {
			rollbackTransaction();
		}
		return result;
		
	}

	public List<Product> allProducts() {
		// TODO Auto-generated method stub
		List<Product> productList = new ArrayList<Product>();
		try {
			beginTransaction();
			Query q = getSession().createQuery("from Product");
			productList = q.list();
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			close();
		}
		return productList;
	}
	

}
