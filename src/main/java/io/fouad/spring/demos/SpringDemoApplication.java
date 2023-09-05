package io.fouad.spring.demos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication implements CommandLineRunner {
    
    private final UserEntityRepository userEntityRepository;
    
    public SpringDemoApplication(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }
    
    @Override
    public void run(String... args) {
        var userEntity = userEntityRepository.queryUserById(1);
        System.out.println("Name = " + userEntity.getName());
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }
}