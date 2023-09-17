package edu.pucmm.pwa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Clase para ejecutar el metodo run de la interfaz ApplicationRunner utilizada cuando arranca el proyecto.
 */
@Order(value = 1) // En caso de tener más de un elemento para definir el orden de ejecución
@Component
public class Bootstrap implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Inicio de aplicación - Bootstrap para definición de variables...");
    }
}
