package com.jensen.steamlite.view.form;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jensen.steamlite.model.bean.UserHandler;
/**
 * Requestscoped bean that is used to
 * send information and to perform operation
 * in UserHandler.
 * 
 * @author Jonas
 * @see UserHandler profile.xhtml
 */
@RequestScoped
@Named
public class ChangeInfoHandler {

	private String userName;
	private String password;
	private String email;
	
	/**
	 * Inject the current http session object
	 * 
	 * @see UserHandler Inject
	 */
	@Inject
	private UserHandler userHandler;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Request to perform the operation in UserHandler.
	 * @see UserHandler
	 * @return String - from the UserHandler method.
	 */
	public String updateInfo() {
		return userHandler.updateInfo(userName, password, email);
	}
}
