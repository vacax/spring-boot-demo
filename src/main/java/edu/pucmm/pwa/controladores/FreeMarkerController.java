package edu.pucmm.pwa.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

/**
 * Created by vacax on 21/09/16.
 */
@Controller()
@RequestMapping("/freemarker")
public class FreeMarkerController {

    @Autowired
    private MessageSource messageSource;

    /**
     * Ejemplo de uso de FreeMarker en nuestro proyecto, la vista es ubicada
     * de forma automatica el retorno de la clase.
     * Ubicada por defecto en resources/templates
     *
     * @param model
     * @param nombre
     * @return
     */
    @RequestMapping("/")
    public String holaMundoFreeMarker(Model model,
                                      @RequestParam(value="nombre", required=false,
                                              defaultValue="Mundo") String nombre){

        //Indicando el modelo que será pasado a la vista.
        model.addAttribute("nombre", nombre);
        System.out.println(".... Simulando cualquier cosa...");

        //Ubicando la vista desde resources/templates
        return "/freemarker/freemarker";
    }

    /**
     * Prueba utilizando los webjars, cargando los BootStrap.
     * @param model
     * @return
     */
    @RequestMapping("/bootstrap")
    public String pruebaBootStrap(Model model){
       return "/freemarker/pruebaBootStrap";
    }

    /**
     * Prueba utilizando los webjars, cargando los BootStrap.
     * @param model
     * @return
     */
    @RequestMapping("/materialize")
    public String pruebaMaterializeCss(Model model){
        return "/freemarker/pruebaMaterializeCss";
    }

    /**
     *
     * @param model
     * @param locale
     * @param nombre
     * @return
     */
    @RequestMapping("/pruebai18n")
    public String pruebaI18n(Model model,
                             Locale locale,
                             @RequestParam(value="nombre",
                                     required=false,
                                     defaultValue="Mundo") String nombre){

        model.addAttribute("nombre", nombre);
        model.addAttribute("saludo", messageSource.getMessage("saludo", null, locale));

      return "/freemarker/pruebaI18n";
    }

}
