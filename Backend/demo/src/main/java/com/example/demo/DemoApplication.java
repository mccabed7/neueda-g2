package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController // must be added to all rest controller classes
public class DemoApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("Person", "password12", 20f));
		userRepository.save(new User("Person2", "password123", 50.51f));
		// userRepository.save(new User("Person3", "password1234", 100f));
		// userRepository.save(new User("Person4", "password1235", 80f));
		userRepository.save(new User("admin", "admin123", 100000f));
	}
}
