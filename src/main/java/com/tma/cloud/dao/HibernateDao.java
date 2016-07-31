package com.tma.cloud.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.tma.cloud.model.User;

public abstract class HibernateDao<T> implements Dao<T> {

	private Class<T> cl;
	
	@Autowired
	protected SessionFactory sessionFactory;

	public HibernateDao() {
		cl = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<T> selectAll() {
		return getSession().createCriteria(User.class).list();
	}

	@Override
	public void insert(T object) {
		getSession().save(object);
	}

	@Override
	public void update(T object) {
		getSession().update(object);
	}

	@Override
	public void delete(int id) {
		T object = (T) getSession().get(cl, id);
		getSession().delete(object);
	}

	@Override
	public T getById(int id) {
		return (T) getSession().get(cl, id);
	}

	@Override
	public T getByField(String field, String value) {
		Criteria criteria = getSession().createCriteria(cl);
		criteria.add(Restrictions.eq(field, value));
		return (T) criteria.uniqueResult();
	}
}
