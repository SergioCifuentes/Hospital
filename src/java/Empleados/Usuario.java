/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Usuario extends Empleado{

    private String username;
    private String password;
    
    public Usuario(String nombre, int cui, Area area,ArrayList<PeriodoLaboral> periodoLaborals,boolean igss,boolean irtra,String username,String password) {
        super(nombre, cui, area,periodoLaborals,igss,irtra);
        this.username=username;
        this.password=password;
        
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
    
    
}
