/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados;

import java.time.LocalDate;

/**
 *
 * @author sergio
 */
public class PeriodoLaboral {
    private String nombre;
    private LocalDate fecha;
    public static final String ID_PROCESO="Proceso De Contratacion";
    
    
    public PeriodoLaboral(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
    
}
