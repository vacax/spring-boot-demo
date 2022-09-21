package edu.pucmm.pwa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1) // En caso de tener más de un elemento para definir el orden de ejecución
@Component
public class Boostrap implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(Boostrap.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Inicio de aplicación - Bootstrap para definición de variables...");
    }
}
