package edu.pucmm.pwa.repositorio.seguridad;

import edu.pucmm.pwa.entidades.seguridad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,String> {

    //Cualquier metodo que necesite incluir.
    
}
