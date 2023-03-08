package com.example.Redis.Spring.Boot;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableRedisDocumentRepositories(basePackages = "com.example.Redis.Spring.Boot.*")

@SpringBootApplication
public class RedisSpringBootApplication {


	public static void main(String[] args) {
		SpringApplication.run(RedisSpringBootApplication.class, args);
	}


}
