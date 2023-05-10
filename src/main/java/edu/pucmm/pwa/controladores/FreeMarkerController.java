package edu.pucmm.pwa.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * Created by vacax on 21/09/16.
 */
@Controller()
@RequestMapping("/freemarker")
public class FreeMarkerController {

    //Inyección de dependencia para la internacionalización
    @Autowired
    private MessageSource messageSource;

    /**
     * Ejemplo de uso de FreeMarker en nuestro proyecto, la vista es ubicada
     * de forma automatica el retorno de la clase.
     * Ubicada por defecto en resources/templates
     *
     * http://localhost:8080/freemarker/
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
        model.addAttribute("con_atributo", messageSource.getMessage("con_atributo", new Object[]{20011136}, locale));

      return "/freemarker/pruebaI18n";
    }

    /**
     * Metodo para probar la redireccion
     * http://localhost:8080/freemarker/redireccion
     * @param model
     * @return
     */
    @RequestMapping("/redireccion")
    public String redireccionPagina(Model model){
        System.out.println("Redireccioando a otra llamada.");
       return "redirect:/freemarker/pruebai18n"; //aplicando la redirección
    }

    /**
     * Metodo para probar el forward, la diferencia es una llamada
     * encadenada en el servidor, no hace otra llamada el cliente.
     * No cambia la URL
     * http://localhost:8080/freemarker/forward
     * @param model
     * @return
     */
    @RequestMapping("/forward")
    public String forwardPagina(Model model){
        System.out.println("Redireccioando a otra llamada.");
        return "forward:/freemarker/bootstrap"; //aplicando la redirección
    }

    /**
     * Permite la salida directa del controlador, sin la necesidad de pasar
     * por el sistema plantilla.
     * @return
     */
    @GetMapping("/directa")
    @ResponseBody
    public String salidaDirecta(){
        return "Presentando el String de forma directa";
    }

}
