package com.me.priyav.dao;

import java.util.ArrayList;
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

		} finally {
			close();
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
		}finally {
			close();
		}

		return result;
	}

	public Customer getCustomer(String username) {

		List<Customer> cList = new ArrayList<Customer>();
		try {
			beginTransaction();
			System.out.println("*****************************************"+username);
			
			Query q = getSession().createQuery("from Customer where custEmail=:user");
			q.setString("user", username);
			cList = q.list();
			System.out.println("*****************************************"+cList);
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			close();
		}
		return cList.get(0);

	}

	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		List<Customer> cList = new ArrayList<Customer>();
		try {
			beginTransaction();
//			System.out.println("*****************************************"+username);
			
			Query q = getSession().createQuery("from Customer where custId =:cid");
			q.setInteger("cid", id);
			cList = q.list();
//			System.out.println("*****************************************"+cList);
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			close();
		}
		return cList.get(0);
	}

}
