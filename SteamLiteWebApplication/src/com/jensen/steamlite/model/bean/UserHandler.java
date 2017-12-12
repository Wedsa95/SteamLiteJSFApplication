package com.jensen.steamlite.model.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.hibernate.Session;

import com.jensen.steamlite.model.database.DatabaseConnectionUtil;
import com.jensen.steamlite.model.entity.Library;
import com.jensen.steamlite.model.entity.User;
import com.jensen.steamlite.model.security.CrypteUtil;
@SessionScoped
@Named
public class UserHandler implements Serializable{
	
	private static final long serialVersionUID = -6114578562850663408L;

	private ResidingUser residingUser;
	
	
	public ResidingUser getResidingUser() {
		return residingUser;
	}

	public void setResidingUser(ResidingUser residingUser) {
		this.residingUser = residingUser;
	}

	@SuppressWarnings("deprecation")
	public String signIn(String userName, String password) {
		
		System.out.println("In Sign INN");
		
		CrypteUtil cryptUtil = new CrypteUtil();
		User user = new User();
		
		try {
			Session session = DatabaseConnectionUtil
					.getSessionFactory().openSession();
			
			user = (User) session.getNamedQuery("GET_USER_BY_NAME").
					setString("name", userName).getSingleResult();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(user.getUserName().equals(userName) &&
				cryptUtil.checkPassword(password, user.getUserPassword())) {
			
			residingUser = new ResidingUser(user);
			Session sessionIn = DatabaseConnectionUtil.getSessionFactory().openSession();
			
			String hachWord = cryptUtil.newSaltAndHach(user.getUserPassword());
			user.setUserPassword(hachWord);
			sessionIn.update(user);
			sessionIn.close();
			return "/faces/store.xhtml?faces-redirect=true";
			
		}else{
			FacesMessage message = new FacesMessage("Wrong User Name or Password");
			FacesContext context = FacesContext.getCurrentInstance();
			
			context.addMessage(null, message);
			
			return "/faces/login.xhtml";
		}
	}
	
	public boolean isSignedIn() {
		return residingUser != null;
	}
	
	public String signOut() {
		System.out.println("In Sign out");
		FacesContext.getCurrentInstance()
		.getExternalContext().invalidateSession();
		
		return "/faces/store.xhtml?faces-redirect=true";
	}
	public void destroySession() {
		System.out.println("In Destroy");
		FacesContext.getCurrentInstance()
		.getExternalContext().invalidateSession();
	}

	@SuppressWarnings("deprecation")
	public String signUp(String userName, String password, String email) {
		
	
		System.out.println("In Sign UPP");
		CrypteUtil cryptUtil = new CrypteUtil();
		User user = new User();
		
		try {
			
			Session session = DatabaseConnectionUtil.getSessionFactory().openSession();
			
			user = (User) session.getNamedQuery("GET_USER_BY_NAME").setString("name", userName).getSingleResult();	
			session.close();
			
		}catch(NoResultException e) {
			System.out.println("No User Found");
			e.printStackTrace();
		}
		
		if(userName != user.getUserName()) {
			
			User newUser = new User();
			String hashWord = cryptUtil.newSaltAndHach(password);
			
			newUser.setUserName(userName);
			newUser.setUserEmail(email);
			newUser.setUserPassword(hashWord);
			
			newUser.setLibrary(new Library());
			
			Session sessionIn = DatabaseConnectionUtil.getSessionFactory().openSession();
			
			sessionIn.save(newUser);
			
			sessionIn.close();
			destroySession();
			return "/faces/login.xhtml";
			
		}else {
			
			FacesMessage message = new FacesMessage("User name already exists!");
			FacesContext context = FacesContext.getCurrentInstance();
			
			context.addMessage(null, message);
			
			destroySession();
			return "/faces/signup.xhtml";
		}
		
		
	}

}
