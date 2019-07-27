/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.Serializable;

/**
 *
 * @author Julian
 */
public class usuario implements Serializable{
    
    private int id;
    private String cuenta;
    private String clave;
    private String cedula;
    private String nombre;
    private String telefono;
    private String rol;

    public usuario(int id, String cuenta, String clave, String cedula, String nombre, String telefono, String rol) {
        this.id = id;
        this.cuenta = cuenta;
        this.clave = clave;
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.rol = rol;
    }

    public usuario() {
    }
    
    //ingreso
    public usuario(String cuenta, String clave, String cedula, String nombre, String telefono, String rol) {
        this.cuenta = cuenta;
        this.clave = clave;
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.rol = rol;
    }
    
    //edicion
    public usuario(String clave, String cedula, String nombre, String telefono, String rol) {
        this.clave = clave;
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.rol = rol;
    }
    
    //funciones set

    public void setId(int id) {
        this.id = id;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    //funciones get

    public int getId() {
        return id;
    }

    public String getCuenta() {
        return cuenta;
    }

    public String getClave() {
        return clave;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRol() {
        return rol;
    }
    
}
