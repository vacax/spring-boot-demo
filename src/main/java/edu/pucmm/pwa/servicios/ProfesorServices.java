package edu.pucmm.pwa.servicios;

import edu.pucmm.pwa.entidades.Profesor;
import edu.pucmm.pwa.repositorio.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vacax on 20/09/16.
 */
@Service
public class ProfesorServices {

    //Inyectando el repositorio
    @Autowired
    private ProfesorRepository profesorRepository;

    public long cantidadProfesores(){
        return profesorRepository.count();
    }

    /**
     * Indica que será una transacción, ver la anotación...
     * Si genero una excepción del tipo runtime realiza el rollback.
     * @param profesor
     * @return
     */
    @Transactional()
    public Profesor creacionProfesor(Profesor profesor){
        profesorRepository.save(profesor);
        return profesor;
    }

    public List<Profesor> profesoresConApellidos(){

        return profesorRepository.findAllByApellidoNotNull();
    }

    /**
     *
     * @param nombre
     * @return
     */
    public List<Profesor> listaProfesorInicia(String nombre){
        System.out.println("Nombre recibido: "+nombre);
        return profesorRepository.findAllByNombreStartingWith(nombre);
    }

    public List<Profesor> listaProfesorIniciaIgnorandoCase(String nombre){
        System.out.println("Nombre recibido: "+nombre);
        return profesorRepository.findAllByNombreStartingWithIgnoreCase(nombre);
    }

    public Profesor profesorPorCedula(String cedula) {
        return profesorRepository.consultaProfesor(cedula);
    }
}
