package com.tma.cloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tma.cloud.dao.UserHibernateDao;
import com.tma.cloud.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("hibernateDao")
	private UserHibernateDao dao;

	@Override
	public List<User> getAllUsers() {
		return dao.selectAll();
	}

	@Override
	public void insertUser(User user) {
		dao.insert(user);
	}

	@Override
	public void updateUser(User user) {
		dao.update(user);
	}

	@Override
	public void deleteUser(int id) {
		dao.delete(id);
	}

	@Override
	public void isUserExist(String username) {
		dao.getByField("username", username);
	}

	@Override
	public User getUserById(int id) {
		return (User) dao.getByField("id", String.valueOf(id));
	}
}
