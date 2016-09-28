package edu.pucmm.pwa.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by vacax on 26/09/16.
 */
@Service
public class CargaData {

    @Autowired
    ProfesorServices profesorServices;

    @PostConstruct
    public void cargarInformacionInicio(){
        System.out.println("Simulando la carga de información.....");
    }

}
