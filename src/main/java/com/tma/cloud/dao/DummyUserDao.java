package com.tma.cloud.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tma.cloud.model.User;

@Repository("dummyUserDao")
public class DummyUserDao implements Dao<User> {

	private Map<Integer, User> users = new HashMap<Integer, User>();

	@Override
	public List<User> selectAll() {
		return new ArrayList<User>(users.values());
	}

	@Override
	public void insert(User object) {
		object.setId(users.size());
		users.put(object.getId(), object);
	}

	@Override
	public void update(User object) {
		users.put(object.getId(), object);
	}

	@Override
	public void delete(int id) {
		users.remove(id);
	}

	@Override
	public User getById(int id) {
		return users.get(id);
	}

	@Override
	public User getByField(String field, String value) {
		return users.get(0);
	}
}
