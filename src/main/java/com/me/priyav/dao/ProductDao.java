package com.me.priyav.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.mindrot.jbcrypt.BCrypt;

import com.me.priyav.pojo.Product;

public class ProductDao extends Dao {

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
		}finally {
			close();
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

	public Product getProductById(int productId) {
		// TODO Auto-generated method stub
		List<Product> p = new ArrayList<Product>();
		try {
			beginTransaction();
			Query q = getSession().createQuery("from Product where productId =:pid");
			q.setInteger("pid", productId);
			p = q.list();
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			close();
		}

		return p.get(0);
	}

	public int deleteProductById(int id) {
		int result = 0;
		try {
			beginTransaction();
			Query q = getSession().createQuery("from Product where productId =:pid");
			q.setInteger("pid", id);
			Product prd = (Product) q.uniqueResult();
			if (prd != null) {
				getSession().delete(prd);
				commit();
				result = 1;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			close();
		}

		return result;
	}

	public int updateProductById(int id) {
		int result = 0;
		try {
			beginTransaction();
			Query q = getSession().createQuery("from Product where productId =:pid");
			q.setInteger("pid", id);
//			Product prd = (Product) q.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			close();
		}

		return result;
	}

	public int updateProduct(Product product) {
		int result = 0;
		try {
			beginTransaction();
			getSession().update(product);
			result=1;
			commit();
		} catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
		return result;

}

//	public List<Product> getProductByCategory() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
