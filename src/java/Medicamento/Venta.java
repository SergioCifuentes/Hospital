/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medicamento;

import Empleados.Areas.Farmaceutico;
import Empleados.Empleado;
import java.time.LocalDate;

/**
 *
 * @author sergio
 */
public class Venta {
    private Empleado empleado;
    private Medicina medicina;
    private LocalDate fecha;

    public Venta(Empleado empleado, Medicina medicina, LocalDate fecha) {
        this.empleado = empleado;
        this.medicina = medicina;
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
    
            
}
