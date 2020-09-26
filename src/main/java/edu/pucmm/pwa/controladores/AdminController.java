package edu.pucmm.pwa.controladores;

import edu.pucmm.pwa.ambientes.AmbienteSesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by vacax on 27/09/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AmbienteSesion ambienteSesion;

    /**
     *
     * @param principal
     * Para conocer el usuario conectado.
     * @return
     */
    @RequestMapping("/")
    public String index(Principal principal){

        return "Usuario Conectado: "+principal.getName()+" --- "+ambienteSesion.getContador();
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/crear")
    public String crear(){
        return "Crear del admin";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/borrar")
    public String borrar(){
        return "Borrar del admin";
    }

    @Secured({"ROLE_USER"})
    @RequestMapping("/actualizar")
    public String actualizar(){
        return "Actualizar del admin";
    }
}
