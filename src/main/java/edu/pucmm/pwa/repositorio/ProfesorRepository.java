package edu.pucmm.pwa.repositorio;

import edu.pucmm.pwa.entidades.Profesor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vacax on 20/09/16.
 */
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    Profesor findByNombre(String nombre);
    List<Profesor> findAllByApellidoNotNull();
    Profesor findByNombreAndApellido(String nombre, String apellido);

    //Documentación de algunas formas de hacer las consultas.
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    List<Profesor> findAllByCiudadIsNotNull();
    List<Profesor> findAllByNombreStartingWith(String nombre);
    //Indicando que ignore el tamaño de la letra.
    List<Profesor> findAllByNombreStartingWithIgnoreCase(String nombre);

    //Ordenando la lista de profesores por fecha.
    List<Profesor> findAllByOrderByFechaNacimientoDesc();

    //Lista los profesores por ciudad paginando.
    List<Profesor> findAllByCiudad(String ciudad, Pageable pageable);

    //Trabajando con los querys de HQL o JPQL.
    @Query("select u from Profesor u where u.cedula = ?1")
    Profesor consultaProfesor(String cedula);

    @Query("select u from Profesor u where u.cedula = :cedula")
    Profesor consultaProfesorCedula(@Param("cedula") String cedula);

    @Query(value = "select * from profesor p where p.cedula = :cedula",
            nativeQuery = true)
    Profesor consultaProfesorCedulaNativo(@Param("cedula") String cedula);
}
