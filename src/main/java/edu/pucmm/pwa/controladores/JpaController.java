package edu.pucmm.pwa.controladores;

import edu.pucmm.pwa.entidades.Profesor;
import edu.pucmm.pwa.servicios.ProfesorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by vacax on 26/09/16.
 */
@Controller // Pude haber utilizado @RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    ProfesorServices profesorServices;

    @ResponseBody //Si utilizo RestController no es necesario incluir.
    @RequestMapping("/profesorInicia/{inicia}")
    public List<Profesor> listaProfesorIniciaCon(@PathVariable String inicia){
       return profesorServices.listaProfesorInicia(inicia);
    }

    @ResponseBody //Si utilizo RestController no es necesario incluir.
    @RequestMapping("/profesorIniciaIgnorandoCase/{inicia}")
    public List<Profesor> listaProfesorIniciaConIgnorandoCase(@PathVariable String inicia){
        return profesorServices.listaProfesorIniciaIgnorandoCase(inicia);
    }

    @ResponseBody //Si utilizo RestController no es necesario incluir.
    @RequestMapping("/profesorByCedula/{cedula}")
    public Profesor profesorPorCedula(@PathVariable String cedula){
        return profesorServices.profesorPorCedula(cedula);
    }
}
