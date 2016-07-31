package com.tma.cloud.authentication;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface AuthenticationMethod {
	void config(AuthenticationManagerBuilder auth) throws Exception;
}
