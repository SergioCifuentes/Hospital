/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medicamento;

import java.time.LocalDate;

/**
 *
 * @author sergio
 */
public class Compra {
    private float costo;
    private LocalDate fecha;

    public Compra(float costo, LocalDate fecha) {
        this.costo = costo;
        this.fecha = fecha;
    }

    public float getCosto() {
        return costo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    
    
}
