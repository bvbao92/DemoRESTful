package com.tma.cloud.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

@Service("jdbcAuthentication")
public class JDBCAuthentication implements AuthenticationMethod {

	@Autowired
	DataSource dataSource;

	@Override
	public void config(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from User where username = ?")
				.authoritiesByUsernameQuery(
						"select u.username, r.name from User u, User_Role ur, Role r where u.id = ur.userId and ur.roleId = r.id and u.username = ?");
	}

}
