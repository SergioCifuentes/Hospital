package Empleados;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sergio
 */
public class Empleado {
    
    private String nombre;
    private int cui;
    private Area area;

    public Empleado(String nombre, int cui, Area area) {
        this.nombre = nombre;
        this.cui = cui;
        this.area = area;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCui() {
        return cui;
    }

    public Area getArea() {
        return area;
    }
    
    
    
}
