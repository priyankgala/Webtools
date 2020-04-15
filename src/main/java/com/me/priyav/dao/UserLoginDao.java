package com.me.priyav.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;

import com.me.priyav.controller.RegisterController;
import com.me.priyav.pojo.Users;

public class UserLoginDao extends Dao {
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	public int registerUser(String username, String password, String userType) {
		int result = 0;
		try {
			Users usr = new Users();
			usr.setUsername(username);
			usr.setUserType(userType);
			usr.setPassword(password);
			beginTransaction();
			getSession().save(usr);
			commit();
			result = 1;

		} catch (HibernateException e) {
			rollbackTransaction();
		}finally {
			close();
		}

		return result;
	}

	public List<Users> authenticateUser(String username, String password) {
		Boolean flag = false;
		List<Users> user = new ArrayList<Users>();
		try {
			beginTransaction();
			Query q = getSession().createQuery("from Users where userName= :username");
			q.setString("username", username);
			user = q.list();
			if (!user.isEmpty()) {
				if (BCrypt.checkpw(password, user.get(0).getPassword())) {
					flag = true;
				}
			}
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			close();
		}
		if (flag)
			return user;
		else
			return null;

	}

}
