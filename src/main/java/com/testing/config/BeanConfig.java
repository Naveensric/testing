package com.testing.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class BeanConfig {

	@Bean
	public ModelMapper mapModel() {
		return new ModelMapper();
	}
	/*
	@Bean
	BCryptPasswordEncoder passwordEcoder() {
		return new BCryptPasswordEncoder();
	}
	*/
	
}
