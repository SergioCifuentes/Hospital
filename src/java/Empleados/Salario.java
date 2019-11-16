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
public class Salario {
    private float monto;
    private LocalDate fecha;

    public Salario(float monto, LocalDate fecha) {
        this.monto = monto;
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
    
}
