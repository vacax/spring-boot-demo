package edu.pucmm.pwa.entidades.seguridad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Usuario implements Serializable {

    @Id
    private String username;
    private String password;
    private boolean activo;
    //Tener todos los campos necesarios de mi modelo..
    private String nombre;

    //relación
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private
    Set<Rol> roles;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
