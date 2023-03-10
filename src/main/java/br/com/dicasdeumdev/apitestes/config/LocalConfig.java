package br.com.dicasdeumdev.apitestes.config;

import br.com.dicasdeumdev.apitestes.domain.User;
import br.com.dicasdeumdev.apitestes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        User u1 = new User(null, "vitor","vitor@gmail.com","123");
        User u2 = new User(null, "mateus","mateus@gmail.com","123");

        repository.saveAll(List.of(u1,u2));
    }
}
