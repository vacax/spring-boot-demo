package edu.pucmm.pwa.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by vacax on 24/09/16.
 */
@Entity
public class Asignatura implements Serializable {

    @Id
    @GeneratedValue
    private
    long id;
    private String clave;
    private String nombre;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio=new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
