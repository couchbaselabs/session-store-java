package com.cb.sessionstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.couchbase.config.annotation.web.http.EnableCouchbaseHttpSession;

@SpringBootApplication
@EnableCouchbaseHttpSession(keepStringAsLiteral = true)
public class SessionStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionStoreApplication.class, args);
	}

}
