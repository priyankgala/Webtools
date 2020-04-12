package com.me.priyav.dao;

import java.util.List;
import com.me.priyav.pojo.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class CustomerDao extends Dao {
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = null;
		try {
			beginTransaction();
			Query query = getSession().createQuery("from Customer");
			customerList = query.list();
			commit();
		} catch (HibernateException e) {
			rollbackTransaction();

		}
		return customerList;

	}
	
	public int addCustomer(Customer customer) {
		int result = 0;
		try {
			beginTransaction();
			getSession().save(customer);
			commit();
			result = 1;

		} catch (HibernateException e) {
			rollbackTransaction();
		}

		return result;
	}

}
