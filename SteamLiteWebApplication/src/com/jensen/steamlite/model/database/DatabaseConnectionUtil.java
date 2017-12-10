package com.jensen.steamlite.model.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jensen.steamlite.model.entity.AchievStatus;
import com.jensen.steamlite.model.entity.Achievment;
import com.jensen.steamlite.model.entity.Category;
import com.jensen.steamlite.model.entity.Game;
import com.jensen.steamlite.model.entity.Library;
import com.jensen.steamlite.model.entity.Rating;
import com.jensen.steamlite.model.entity.User;



public class DatabaseConnectionUtil {


	
	private static SessionFactory sessionFactory;
	
	 static {
		try {
			sessionFactory = new Configuration()
				.configure("/com/jensen/steamlite/model/database/hibernate.cfg.xml")
				.buildSessionFactory();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void closeSessionFactory() {
		sessionFactory.close();
	}
		

}
