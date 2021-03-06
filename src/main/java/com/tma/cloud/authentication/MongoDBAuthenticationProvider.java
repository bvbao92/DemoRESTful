package com.tma.cloud.authentication;

import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tma.cloud.model.Client;

@Service("mongoDBAuthentication")
public class MongoDBAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
		implements AuthenticationMethod {

//	@Autowired
	private MongoCollection users;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;

		try {
			Client client = users.findOne("{#: #}", Client.USERNAME, username).as(Client.class);
			loadedUser = new User(client.getUsername(), client.getPassword(), client.getRoles());
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}

	@Override
	public void config(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this);
	}
}