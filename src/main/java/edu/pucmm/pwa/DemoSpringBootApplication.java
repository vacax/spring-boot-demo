package edu.pucmm.pwa;

import edu.pucmm.pwa.entidades.Profesor;
import edu.pucmm.pwa.repositorio.ProfesorRepository;
import edu.pucmm.pwa.servicios.seguridad.SeguridadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication()
public class DemoSpringBootApplication {



	public static void main(String[] args) {

		//Crear el template
		//SpringApplication.run(DemoSpringBootApplication.class, args);

		ApplicationContext applicationContext = SpringApplication.run(DemoSpringBootApplication.class, args);
		String[] lista = applicationContext.getBeanDefinitionNames();
		System.out.println("====== Beans Registrados =====");
		for(String bean : lista){
			System.out.println(""+bean);
		}
		System.out.println("====== FIN Beans Registrados =====");

		//Sin Inyección de dependencia:
		ProfesorRepository profesorRepository = (ProfesorRepository) applicationContext.getBean("profesorRepository");
		Profesor profesor = new Profesor();
		profesor.setNombre("Carlos");
		profesor.setApellido("Camacho");
		profesor.setFechaNacimiento(new Date());
		profesor.setCedula("031-9999999-0");
		profesorRepository.save(profesor);

		SeguridadServices seguridadServices = (SeguridadServices) applicationContext.getBean("seguridadServices");
		seguridadServices.crearUsuarioAdmin();
	}
}
