package com.Platinum.Asixstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class AsixStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsixStoreApplication.class, args);
	}
	@Bean

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods(CorsConfiguration.ALL)
						.allowedHeaders(CorsConfiguration.ALL)
						.allowedOriginPatterns(CorsConfiguration.ALL).allowedOrigins("*");

//				registry.addMapping("/**").allowedMethods("POST").allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
