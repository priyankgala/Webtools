package com.me.priyav.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.priyav.pojo.Cart;
import com.me.priyav.pojo.CartItem;
import com.me.priyav.pojo.Product;

public class CartDao extends Dao {

	public int addCartItem(CartItem cartItem) {
		int result = 0;
		try {
			beginTransaction();
			getSession().save(cartItem);
			commit();
			result = 1;
		} catch (HibernateException e) {
			rollbackTransaction();
		} finally {
			close();
		}
		return result;
	}

	public int updateCartItem(CartItem cartItem) {
		int result = 0;
		try {
			beginTransaction();
			getSession().update(cartItem);
			result = 1;
			commit();
		} catch (HibernateException e) {
			rollbackTransaction();
		} finally {
			close();
		}
		return result;
	}

	public int removeProdcutById(int id) {
		int result = 0;
		try {
			beginTransaction();
			Query q = getSession().createQuery("from CartItem where cartItemId =:cartid");
			q.setInteger("cartid", id);
			CartItem CI= (CartItem) q.uniqueResult();
			if (CI != null) {
				getSession().delete(CI);
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

	public List<CartItem> getCartListByProductid() {
		// TODO Auto-generated method stub
		List<CartItem> cList = new ArrayList<CartItem>();
		try {
			beginTransaction();
			Query q = getSession().createQuery("from CartItem");
			cList = q.list();
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			close();
		}
		return cList;
	}
	
	public int removeProdcutByProductId(int id) {
		int result = 0;
		try {
			beginTransaction();
			Query q = getSession().createQuery("from CartItem where productId =:pid");
			q.setInteger("pid", id);
			CartItem CI= (CartItem) q.uniqueResult();
			if (CI != null) {
				getSession().delete(CI);
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

	public int removeProdcutByCartId(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			beginTransaction();
			Query q = getSession().createQuery("from CartItem where cartId =:pid");
			q.setInteger("pid", id);
			CartItem CI= (CartItem) q.uniqueResult();
			if (CI != null) {
				getSession().delete(CI);
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

	public void save(Cart cart) {
		try {
			beginTransaction();
			getSession().update(cart);
			commit();
		} catch (HibernateException e) {
			rollbackTransaction();
		} finally {
			close();
		}
	}

}
