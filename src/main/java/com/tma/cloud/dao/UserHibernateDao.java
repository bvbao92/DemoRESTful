package com.tma.cloud.dao;

import org.springframework.stereotype.Repository;

import com.tma.cloud.model.User;

@Repository("hibernateDao")
public class UserHibernateDao extends HibernateDao<User> {

}
