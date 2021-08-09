package com.example.tweetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class TweetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetApiApplication.class, args);
	}

}
