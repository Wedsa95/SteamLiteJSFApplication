package com.jensen.steamlite.view.form;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jensen.steamlite.model.bean.UserHandler;

/**
 * Requestscoped bean that is used to 
 * call a method in the UserHandler.
 * 
 * @author Jonas
 * @see UserHandler slwt_navigation.xhtml
 */
@RequestScoped
@Named
public class SignOutHandler {
	
	/**
	 * Inject the current http session object
	 * 
	 * @see UserHandler Inject
	 */
	@Inject
	private UserHandler userHandler;

	/**
	 * Request to perform the operation in UserHandler.
	 * 
	 * @see UserHandler
	 * @return String - from the UserHandler method
	 */
	public String sendTo() {
		return userHandler.signOut();
	}
}
