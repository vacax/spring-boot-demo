package edu.pucmm.pwa.controladores;

import edu.pucmm.pwa.ambientes.AmbienteSesion;
import edu.pucmm.pwa.encapsulacion.Estudiante;
import edu.pucmm.pwa.entidades.Profesor;
import edu.pucmm.pwa.servicios.EstudianteServices;
import edu.pucmm.pwa.servicios.ProfesorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Clase que representa un controlador para trabajar con el API REST.
 * Created by vacax on 20/09/16.
 */
@RestController
@RequestMapping("/api/")
public class RestApiController {

    //Inyección de dependecia.
    @Autowired
    private EstudianteServices estudianteServices;

    @Autowired
    private ProfesorServices profesorServices; //= new ProfesorServices(); //no puedo instanciar.

    //Inyectando los ambientes.
    @Autowired
    private AmbienteSesion ambienteSesion;

    //Inyectado la configuración desde las propiedades.
    @Value("${holaMundoControlador.mensaje}")
    private String mensajeDesdePropiedades;

    
    @RequestMapping("/")
    public String holaMundo(HttpSession session){
        Integer contador = (Integer) session.getAttribute("contador");
        if(contador==null){
            contador=0;
        }
        contador++;
        session.setAttribute("contador", contador);
        return "Hola Mundo Desde Spring Boot: "+ambienteSesion.getContador()+" - contador Sesion...: "+contador;
    }

    @RequestMapping("/profesores")
    public String cantidadProfesores(){
        return ""+profesorServices.cantidadProfesores();
    }

    @RequestMapping("/profesoresConApellidos")
    public List<Profesor> profesoresConApellidos(){
        return profesorServices.profesoresConApellidos();
    }

    @RequestMapping("/mensaje")
    public String mensajeArchivo(){
        return ""+mensajeDesdePropiedades;
    }

    @RequestMapping("/estudiantes")
    public List<Estudiante> listaEstudiantes(){
        return estudianteServices.listaEstudiantes();
    }

    /**
     * Recuperando información desde la URL
     * @param matricula
     * @return
     */
    @RequestMapping(value = "/estudiante/{matricula}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Estudiante getEstudiante(@PathVariable() int matricula){
        return new Estudiante(matricula);
    }

    /**
     *
     * @param matricula
     * @param nombre
     * @return
     */
    @RequestMapping(value = "/estudiante", method = RequestMethod.PUT)
    //@PutMapping(path = "/estudiante")
    public String insertarEstudiante(@RequestParam() int matricula, @RequestParam String nombre){
        Estudiante estudiante = new Estudiante(matricula);
        estudiante.setNombre(nombre);
        return ""+estudiante.getMatricula();
    }

    /**
     * Proceando objetos de forma directa.
     * @param estudiante
     * @return
     */
    @RequestMapping(value = "/estudiante", method = RequestMethod.POST, consumes = {"application/json"})
    public Estudiante actualizandoEstudiante(@RequestBody Estudiante estudiante){
        System.out.println("El estudiante recibido: "+estudiante.getMatricula());
       return estudiante;
    }

    /**
     * Parsear directamente un formulario a un objeto.
     * @param estudiante
     * @return
     */
    @PostMapping(path = "/estudiante", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String parsearEstudianteFormulario(Estudiante estudiante){
        return estudiante.toString();
    }

}
