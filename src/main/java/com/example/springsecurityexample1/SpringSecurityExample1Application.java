package com.example.springsecurityexample1;

import com.example.springsecurityexample1.models.User;
import com.example.springsecurityexample1.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SpringSecurityExample1Application {

	private static long id = 1L;

	public static void main(String[] args) {
		UserRepository repository =  SpringApplication.run(SpringSecurityExample1Application.class, args).getBean(UserRepository.class);
		create(repository,  "admin");
		create(repository,  "auth");
		create(repository,  "user");
		create(repository,  "simple");
	}

	private static void create(UserRepository repository, String username) {
		User user = new User();
		user.setId(id++);
		user.setUsername(username);
		user.setPassword(username);
		user.setRole(username);
		repository.save(user);
	}

}