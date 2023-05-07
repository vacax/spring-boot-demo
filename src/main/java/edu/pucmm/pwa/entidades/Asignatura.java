package edu.pucmm.pwa.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vacax on 24/09/16.
 */
@Entity
@Data
public class Asignatura implements Serializable {

    @Id
    @GeneratedValue
    private
    long id;
    private String clave;
    private String nombre;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio=new Date();

}
