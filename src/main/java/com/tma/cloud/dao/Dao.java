package com.tma.cloud.dao;

import java.util.List;

public interface Dao<T> {
	List<T> selectAll();
	void insert(T object);
	void update(T object);
	void delete(int id);
	T getById(int id);
	T getByField(String field, String value);
}

