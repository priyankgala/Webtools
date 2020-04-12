package com.me.priyav.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import org.hibernate.Session;

public class Dao {
	private static final ThreadLocal sessionThread = new ThreadLocal();
	static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	private Session session = null;

	public static Session getSession() {

		Session session = (Session) Dao.sessionThread.get();

		if (session == null) {
			session = sf.openSession();
			Dao.sessionThread.set(session);
		}
		return session;
	}

	protected void beginTransaction() {
		getSession().beginTransaction();
	}

	protected void commit() {
		getSession().getTransaction().commit();
	}

	protected void close() {
		getSession().close();
	}

	protected void rollbackTransaction() {
		getSession().getTransaction().rollback();
	}

}
