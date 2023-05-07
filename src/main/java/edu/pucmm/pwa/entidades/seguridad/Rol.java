package edu.pucmm.pwa.entidades.seguridad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Rol implements Serializable {

    @Id
    private String role;

    public Rol(){

    }

    public Rol(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
