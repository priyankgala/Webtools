package com.me.priyav.dao;

import org.hibernate.HibernateException;

import com.me.priyav.pojo.CustOrder;

public class OrderDao extends Dao{

	public void saveOrder(CustOrder order) {
		try {
			beginTransaction();
			getSession().save(order);
			commit();
		} catch (HibernateException e) {
			rollbackTransaction();
		}finally {
			close();
		}
		
	}
}
