/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados.Areas;

import Empleados.Area;
import Empleados.PeriodoLaboral;
import Empleados.Usuario;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Consultor extends Usuario{
    
    public Consultor(String nombre, int cui, Area area, boolean igss, boolean irtra , ArrayList<PeriodoLaboral> periodoLaborals, String username, String password) {
        super(nombre, cui, area, periodoLaborals, igss, irtra, username, password);
    }
    
}
