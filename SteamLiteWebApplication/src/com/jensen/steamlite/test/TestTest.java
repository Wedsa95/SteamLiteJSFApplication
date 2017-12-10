package com.jensen.steamlite.test;

import org.hibernate.Session;

import com.jensen.steamlite.model.database.DatabaseConnectionUtil;
import com.jensen.steamlite.model.entity.User;

public class TestTest {

	public static void main (String [] args) {
//		Session session = DatabaseConnectionUtil.getInstance().getSessionFactory().getCurrentSession();
//	
//		User user = session.get(User.class, 1);
//		
//		System.out.println(user.getUserEmail());
//		
//		session.close();
//		
		
		Session session = DatabaseConnectionUtil.getSessionFactory().openSession();
		
		User user = session.get(User.class, 1);
		
		System.out.println(user.getUserName());
		
		session.close();
	}
}
