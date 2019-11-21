/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados.Areas;

import Empleados.Area;
import Empleados.PeriodoLaboral;
import Empleados.Usuario;
import Medicamento.Venta;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Farmaceutico extends Usuario{
    private ArrayList<Venta> ventas;
    
    public Farmaceutico(String nombre, int cui, Area area, boolean igss, boolean irtra, ArrayList<PeriodoLaboral> periodoLaborals, String username, String password) {
        super(nombre, cui, area, periodoLaborals, igss, irtra, username, password);
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }
    
    
    
}
