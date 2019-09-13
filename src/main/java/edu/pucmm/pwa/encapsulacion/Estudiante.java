package edu.pucmm.pwa.encapsulacion;

/**
 * Created by vacax on 20/09/16.
 */
public class Estudiante {

    private int matricula;
    private String nombre;

    public Estudiante(){

    }

    public Estudiante(int matricula){
        this.matricula = matricula;
        nombre = "Estudiante "+matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
