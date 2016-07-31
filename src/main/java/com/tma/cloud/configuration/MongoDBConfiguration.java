package com.tma.cloud.configuration;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.context.annotation.Bean;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class MongoDBConfiguration {

	@Bean
	public Jongo jongo() {
		DB db;
		try {
			db = new MongoClient("localhost", 27017).getDB("test");
		} catch (Exception e) {
			throw new MongoException("Connection error : ", e);
		}
		return new Jongo(db);
	}

	@Bean
	public MongoCollection users() {
		return jongo().getCollection("users");
	}
}
