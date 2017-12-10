package com.jensen.steamlite.model.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jensen.steamlite.model.entity.User;
@ManagedBean
@SessionScoped
public class ResidingUser {

	private boolean isLoggedIn;
	private User user;
	
	public ResidingUser(boolean isLoggedIn, User user) {
		
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
