package com.relationship;

import com.relationship.validator.NamingConventionValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RelationshipApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(RelationshipApplication.class, args);
//		NamingConventionValidator validator = context.getBean(NamingConventionValidator.class);
//		String basePackage = "com.relationship";
//		validator.validateNamingConventions(basePackage);
	}

}
