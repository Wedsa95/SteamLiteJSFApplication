package com.jensen.steamlite.model;

import java.util.List;

import org.hibernate.Session;

import com.jensen.steamlite.model.database.DatabaseConnectionUtil;
import com.jensen.steamlite.model.entity.User;

public class UserDao implements GenericCRUDInterface<User> {

	@Override
	public List<User> readList(User x) {
		
		
		
		
		return null;
	}

	@Override
	public User read(User x) {
		return x;
		
	}

	@Override
	public User create(User x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User delete(User x) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
