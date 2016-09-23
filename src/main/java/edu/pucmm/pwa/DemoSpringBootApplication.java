package edu.pucmm.pwa;

import edu.pucmm.pwa.entidades.Profesor;
import edu.pucmm.pwa.repositorio.ProfesorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoSpringBootApplication {

	public static void main(String[] args) {

		//SpringApplication.run(DemoSpringBootApplication.class, args);

		ApplicationContext applicationContext = SpringApplication.run(DemoSpringBootApplication.class, args);
		String[] lista = applicationContext.getBeanDefinitionNames();
		for(String bean : lista){
			System.out.println(""+bean);
		}

		ProfesorRepository profesorRepository = (ProfesorRepository) applicationContext.getBean("profesorRepository");
		Profesor profesor = new Profesor();
		profesor.setNombre("Carlos");
		profesor.setApellido("Camacho");
		profesorRepository.save(profesor);
	}
}
