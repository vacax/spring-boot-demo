package edu.pucmm.pwa.repositorio;

import edu.pucmm.pwa.entidades.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vacax on 20/09/16.
 */
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    Profesor findByNombre(String nombre);
    List<Profesor> findAllByApellidoNotNull();
    Profesor findByNombreAndApellido(String nombre, String apellido);
}
