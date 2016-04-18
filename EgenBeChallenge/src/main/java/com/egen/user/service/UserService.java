package com.egen.user.service;

import java.util.List;

import com.egen.model.User;
import com.egen.user.mongo.UserMongoDB;

public class UserService {


	public String createUser(User user) {
		
		return UserMongoDB.insertUser(user);
	}
	
	public String updateUser(User user) {
		
		return UserMongoDB.updateUser(user);
	}

	public List<String> getUsers() {
		
		return UserMongoDB.getAllUsers();
	}

}
