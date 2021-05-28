package com.auth;

import com.auth.model.User;
import com.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication {
    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Arrays.asList(
                new User(101, "user1", "pwd1", "user1@user.com"),
                new User(102, "user2", "pwd2", "user2@user.com"),
                new User(103, "user3", "pwd3", "user3@user.com"));
        repository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
