package com.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan
@EnableWebMvc
public class SpringBootShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootShiroApplication.class, args);
	}
}
