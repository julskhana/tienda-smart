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
public class tienda implements Serializable{
    
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String celular;
    private String propietario;

    public tienda(int id, String nombre, String direccion, String telefono, String celular, String propietario) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.propietario = propietario;
    }
    
    
}
