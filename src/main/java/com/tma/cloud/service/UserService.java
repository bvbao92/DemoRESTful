package com.tma.cloud.service;

import java.util.List;

import com.tma.cloud.model.User;

public interface UserService {
	List<User> getAllUsers();
	
	User getUserById(int id);

	void insertUser(User user);
	
	void updateUser(User user);

	void deleteUser(int id);

	void isUserExist(String username);
}
