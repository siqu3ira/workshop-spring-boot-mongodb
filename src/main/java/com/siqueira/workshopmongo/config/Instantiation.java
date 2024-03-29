package com.siqueira.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.siqueira.workshopmongo.domain.Post;
import com.siqueira.workshopmongo.domain.User;
import com.siqueira.workshopmongo.dto.AuthorDTO;
import com.siqueira.workshopmongo.dto.CommentDTO;
import com.siqueira.workshopmongo.repository.PostRepository;
import com.siqueira.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maurilio = new User(null, "Maurílio Siqueira", "maurilio@gmail.com");
		User daniel = new User(null, "Daniel Rodrigues", "daniel@gmail.com");
		User paulo = new User(null, "Paulo Ribeiro", "paulo@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maurilio, daniel, paulo));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maurilio));
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maurilio));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano", sdf.parse("21/03/2018"), new AuthorDTO(daniel));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(paulo));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(daniel));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	
		maurilio.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(maurilio);
	}

}
