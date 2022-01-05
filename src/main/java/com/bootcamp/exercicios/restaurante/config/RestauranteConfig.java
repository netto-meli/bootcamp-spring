package com.bootcamp.exercicios.restaurante.config;

import com.bootcamp.exercicios.restaurante.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfig implements CommandLineRunner {

    @Autowired
    private RestauranteRepository mesas;

    @Override
    public void run(String... args) {
        mesas.abreRestaurante();
    }
}
