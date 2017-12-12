

package com.jensen.steamlite.model.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.jensen.steamlite.model.database.DatabaseConnectionUtil;
import com.jensen.steamlite.model.entity.Game;
import com.jensen.steamlite.model.entity.Library;

@ManagedBean
@ViewScoped
public class LibraryHandler {

	private List<Game> games;
	
	@ManagedProperty(value="#{userHandler.residingUser}")
	private ResidingUser residingUser; 

	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public List<Game> retrieveGamesList(){
		FacesContext.getCurrentInstance();
		try {
			Session session = DatabaseConnectionUtil.getSessionFactory().openSession();
			
			session.get(Library.class, residingUser.getUser().getUserId());
		} catch (Exception e) {
			
		}
		
		return games;
	}
	
}
