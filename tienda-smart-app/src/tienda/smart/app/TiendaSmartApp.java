/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.smart.app;

import Formularios.frmLogin;
import bd.ConexionBD;

/**
 *
 * @author Julian
 */
public class TiendaSmartApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Tienda Smart");
        System.out.println("Bienvenido...\nJulianB");
        
        ConexionBD c = new ConexionBD();
        try {
            c.conectar();
            c.desconectar();
        } catch (Exception e) {
            System.out.println("Error DB - "+e);
        }
            
        
        frmLogin inicio = new frmLogin();
        inicio.setVisible(true);
    }
    
}
