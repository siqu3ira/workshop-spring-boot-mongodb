package com.siqueira.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.siqueira.workshopmongo.domain.User;
import com.siqueira.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User maurilio = new User(null, "Maurílio Siqueira", "maurilio@gmail.com");
		User daniel = new User(null, "Daniel Rodrigues", "daniel@gmail.com");
		User paulo = new User(null, "Paulo Ribeiro", "paulo@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maurilio, daniel, paulo));
	}

}
