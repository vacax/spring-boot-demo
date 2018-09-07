package edu.pucmm.pwa.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller() //indica que es un controlador.
@RequestMapping(path = "/thymeleaf")
public class ThymeleafController {

    /**
     * Llamada a la vista index.html con la platilla thymeleaf
     * @param model
     * Representa el modelo que será pasado en la vista.
     * @param matricula
     * Valor como parametro.
     *
     * Para ejecutar: http://localhost:8080/thymeleaf/?matricula=20011136
     * @return
     */
    @RequestMapping(path = "/")
    public String index(Model model, @RequestParam(name = "matricula") int matricula){
        //
        model.addAttribute("titulo", "Pagina desde Thymeleaf");
        model.addAttribute("mensaje", "Pagina desde Thymeleaf");
        model.addAttribute("matricula", matricula);
        // direccioando a la vista.
        return "/thymeleaf/index";
    }
}
