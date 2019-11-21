package Empleados;

import java.time.LocalDate;
import java.util.ArrayList;

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
    private ArrayList<PeriodoLaboral> periodos;
    private ArrayList<Salario> salario;
    private boolean igss;
    private boolean irtra;

    public Empleado(String nombre, int cui, Area area,ArrayList<PeriodoLaboral> periodos,boolean igss,boolean irtra) {
        this.nombre = nombre;
        this.cui = cui;
        this.area = area;
        this.periodos=periodos;
        this.igss=igss;
        this.irtra=irtra;
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

    public ArrayList<PeriodoLaboral> getPeriodos() {
        return periodos;
    }

    public boolean isIgss() {
        return igss;
    }

    public boolean isIrtra() {
        return irtra;
    }

    public ArrayList<Salario> getSalario() {
        return salario;
    }
    
    public LocalDate obtenerFechaContratado(){
        for (int i = 0; i < periodos.size(); i++) {
            if (periodos.get(i).getNombre().equals(PeriodoLaboral.ID_CONTRATADO)) {
                return periodos.get(i).getFecha();
            }
            
        }
        return null;
    }

    public void setSalario(ArrayList<Salario> salario) {
        this.salario = salario;
    }
     
    
}
