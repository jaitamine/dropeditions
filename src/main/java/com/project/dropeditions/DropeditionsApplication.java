package com.project.dropeditions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DropeditionsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DropeditionsApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE(){
		
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
