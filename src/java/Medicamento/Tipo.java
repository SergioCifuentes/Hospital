/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medicamento;

/**
 *
 * @author sergio
 */
public class Tipo {
    private String codigo;
    private String nombre;
    private String descripcion;
    private float precio;
    private int cantidadMinima;

    public Tipo(String codigo, String nombre, String descripcion, float precio, int cantidadMinima) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadMinima = cantidadMinima;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }
    
}
