package com.jensen.steamlite.model.bean;

import com.jensen.steamlite.model.entity.User;
/**
 * The http session Object for 
 * the UserHandler.
 * @author Jonas
 * @see UserHandler
 */
public class ResidingUser {
	/**
	 * A copy of the information 
	 * for a user on the database. 
	 */
	private User user;

	/**
	 * Constructor that takes in a
	 * User entity.
	 * 
	 * @param user
	 * @see User
	 */
	public ResidingUser(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
}
