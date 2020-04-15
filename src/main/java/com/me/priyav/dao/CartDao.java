package com.me.priyav.dao;

import org.hibernate.HibernateException;

import com.me.priyav.pojo.CartItem;

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

}
