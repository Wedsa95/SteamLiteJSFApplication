package com.jensen.steamlite.model.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import com.jensen.steamlite.model.database.DatabaseConnectionUtil;
import com.jensen.steamlite.model.entity.Library;
import com.jensen.steamlite.model.entity.User;
import com.jensen.steamlite.model.security.CrypteUtil;
@SessionScoped
@Named
public class UserHandler implements Serializable{
	
	private static final long serialVersionUID = -6114578562850663408L;

	/**
	 * The Object of the user
	 * in the http session.
	 * 
	 * @see ResidingUser User
	 */
	private ResidingUser residingUser = null;
	
	
	public ResidingUser getResidingUser() {
		return residingUser;
	}

	public void setResidingUser(ResidingUser residingUser) {
		this.residingUser = residingUser;
	}

	/**
	 * Uses userName to find the user
	 * on the database. Then if the given password 
	 * matches the hashed on the retrieved user 
	 * a session adds the user to the session.
	 * 
	 * @see CrypteUtil
	 * @param userName
	 * @param password
	 * @return String - Redirects to login or store page
	 */
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
	
	/**
	 * Checks if the ResidingUser is set.
	 * 
	 * @return boolean - true if ResidingUser i set.
	 */
	public boolean isSignedIn() {
		return residingUser != null;
	}
	/**
	 * Retrieved the Faces session and invalidates it.
	 * The redirects the user. 
	 * 
	 * @return String - A redirect to the appropriate index page
	 */
	public String signOut() {
		System.out.println("In Sign out");
		FacesContext.getCurrentInstance()
		.getExternalContext().invalidateSession();
		
		return "/faces/store.xhtml?faces-redirect=true";
	}
	/**
	 * Retrieved the Faces session and invalidates it.
	 */
	public void destroySession() {
		System.out.println("In Destroy");
		FacesContext.getCurrentInstance()
		.getExternalContext().invalidateSession();
	}
	/**
	 * Uses the userName to find out if a user 
	 * on the database already exists. If not
	 * a new user is created using the in 
	 * parameters send it to the database. 
	 * 
	 * @see CrypteUtil DatabaseConnectionUtil
	 * @param userName
	 * @param password
	 * @param email
	 * @return String - Redirect to the login page or signup page
	 */
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

	/**
	 * Updates the ResidingUser in the http session 
	 * information on the database.
	 * Using the in parameters.
	 * 
	 * @param userName
	 * @param password
	 * @param email
	 * @return String - Redirects to login page
	 */
	public String updateInfo(String userName, String password, String email) {
		CrypteUtil cryptUtil = new CrypteUtil();
		String newPassword = cryptUtil.newSaltAndHach(password);
		User user = residingUser.getUser();
		User updateUser = null;
		try {
			Session session = DatabaseConnectionUtil
					.getSessionFactory().openSession();
			
			updateUser = session.get(User.class, user.getUserId());
			updateUser.setUserName(userName);
			updateUser.setUserPassword(newPassword);
			updateUser.setUserEmail(email);
			
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		destroySession();
		return "/faces/login.xhtml";
	}
	
	/**
	 * Deletes the ResidingUser on the http session
	 * from the database.
	 * 
	 * @return String - Redirects to login page
	 */
	public String deleteUser() {
		User deleteUser = null;
		try {
			Session session = DatabaseConnectionUtil
					.getSessionFactory().openSession();
			
			deleteUser = session.get(User.class
					,residingUser.getUser().getUserId());
			
			session.delete(deleteUser);
			
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
			
		destroySession();
		return "/faces/login.xhtml";
		
	}

}
