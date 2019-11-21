/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados;

/**
 *
 * @author sergio
 */
public class Area {
    private String nombre;
    private String codigo;
    private boolean usuario;

    public  static final String CONDIGO_CONTRATADOR="A0001"; 
    public  static final String CONDIGO_FARMACEUTICO="A0003";
    public  static final String CONDIGO_CONSULTOR="A0004";
    
    public Area(String nombre, String codigo, boolean usuario) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isUsuario() {
        return usuario;
    }
    
    
}
