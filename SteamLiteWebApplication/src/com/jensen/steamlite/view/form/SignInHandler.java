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
 * @see UserHandler login.xhtml
 */
@RequestScoped
@Named
public class SignInHandler {

	private String userName;
	private String password;
	
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
	/**
	 * Request to perform the operation in UserHandler.
	 * @see UserHandler
	 * @return String - from the UserHandler method
	 */
	public String sendTo() {
		return userHandler.signIn(userName, password);
	}
}
