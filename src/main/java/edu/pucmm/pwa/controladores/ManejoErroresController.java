package edu.pucmm.pwa.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Clase para demostrar el manejo de los errores.
 * Created by vacax on 29/09/16.
 */
@RestController
@RequestMapping("/error")
public class ManejoErroresController {


    @RequestMapping("/prueba")
    public String pruebaError(@RequestParam("nombre") String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            //Si lo mando vacio dispara la excepcióñ indicada.
            throw new IllegalArgumentException("El nombre no puede ser vacio....");
        }
        return String.format("Hola %s!", nombre);
    }

    /**
     * Captura el la excepcion y muestro el mensaje.
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String manejoPeticionMalElaborada(IllegalArgumentException e){
        return e.getMessage();
    }

}
