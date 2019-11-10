/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados.Areas;

import Empleados.Area;
import Empleados.Usuario;

/**
 *
 * @author sergio
 */
public class Contratador extends Usuario{
    public Contratador(String nombre, int cui, Area area, String username, String password) {
        super(nombre, cui, area, username, password);
    }
            
}
