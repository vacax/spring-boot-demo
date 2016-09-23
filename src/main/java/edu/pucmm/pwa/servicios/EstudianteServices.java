package edu.pucmm.pwa.servicios;

import edu.pucmm.pwa.encapsulacion.Estudiante;
import edu.pucmm.pwa.repositorio.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vacax on 20/09/16.
 */
@Service
public class EstudianteServices {


    /**
     * Simulando una conexión a una base de datos...
     * @return
     */
    public List<Estudiante> listaEstudiantes(){
        List<Estudiante> lista = new ArrayList<>();
        lista.add(new Estudiante(20011136));
        lista.add(new Estudiante(20021136));
        lista.add(new Estudiante(20031136));
        lista.add(new Estudiante(20041136));
        lista.add(new Estudiante(20051136));
        return lista;
    }


}
