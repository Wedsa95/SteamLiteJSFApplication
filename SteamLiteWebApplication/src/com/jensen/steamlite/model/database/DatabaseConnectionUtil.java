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


	private static DatabaseConnectionUtil instance = new DatabaseConnectionUtil();
	private SessionFactory sessionFactory;
	
	/**
	 * An private constructor that creates
	 * only one instance of a {@link SessionFactory}
	 * @see Hibernate documentation 
	 */
	private DatabaseConnectionUtil(){
		this.sessionFactory = buildSessionFactory();
	}
	
	/**
	 * An private method that returns a configured
	 * and entity inserted {@link SessionFactory}
	 * 
	 * @return SessionFactory 
	 * @see Hibernate documentation 
	 */
	private SessionFactory buildSessionFactory() {
		return new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Game.class)
				.addAnnotatedClass(Category.class)
				.addAnnotatedClass(Library.class)
				.addAnnotatedClass(Rating.class)
				.addAnnotatedClass(AchievStatus.class)
				.addAnnotatedClass(Achievment.class)
				.buildSessionFactory();
	}
	
	/**
	 * An static method that instantiate the 
	 * constructor only ones. And returns the 
	 * {@link ConnectionSingelton}
	 * 
	 * @return DatabaseConnectionUtil 
	 * @see Hibernate documentation 
	 */
	public static DatabaseConnectionUtil getInstance() {
		if(instance == null){
			instance = new DatabaseConnectionUtil();
		}
		return instance;
	}
	/**
	 * Returns the one and only {@link SessionFactory} 
	 * object that is used to communicate to database.
	 * 
	 * @return sessionFactory 
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	/**
	 * Closes the {@link SessionFactory}.
	 * And there by the link to the database.
	 * 
	 * @see Hibernate documentation 
	 */
	public void closeSessionFactory() {
		sessionFactory.close();
	}
	
	public Session openConnection() {
		SessionFactory factory = DatabaseConnectionUtil.getInstance().getSessionFactory();
		Session session = factory.getCurrentSession();
		return session;
	}
	
	public void closeConnection(Session session) {
		session.close();
	}

}
