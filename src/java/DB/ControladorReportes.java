/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import DummiesReportes.MedicamentosDTO;
import Empleados.Area;
import Empleados.Empleado;
import Medicamento.Medicina;
import Medicamento.Tipo;
import Medicamento.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class ControladorReportes extends Coneccion {

    private final static String ST_PERIODOS = " SELECT Cui  From PeriodoLaboral";
    private final static String ST_VENTA = " SELECT *  From Venta";
    private final static String ST_WHERE = " WHERE ";
    private final static String ST_CONTRAT = " Nombre = 'Contratado'";
    private final static String ST_RETIRADOS = " Nombre = 'Despedido' OR Nombre = 'Renuncio'";
    private final static String ST_FECHA_INICIO = " Fecha> ? ";
    private final static String ST_AND = " AND ";
    private final static String ST_FECHA_FINAL = " Fecha< ? ";

    public ArrayList<String[]> obtenerContratados(LocalDate inicio, LocalDate fin, Area area) {
        ArrayList<String[]> contratados = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            ResultSet resultado2 = null;
            PreparedStatement declaracionPreparada = null;
            if (inicio == null && fin == null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_PERIODOS + ST_WHERE + ST_CONTRAT);
            }
            if (inicio != null && fin != null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_PERIODOS + ST_WHERE + ST_CONTRAT + ST_AND + ST_FECHA_INICIO + ST_AND + ST_FECHA_FINAL);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                declaracionPreparada.setString(2, String.valueOf(fin));
            }
            if (inicio != null && fin == null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_PERIODOS + ST_WHERE + ST_CONTRAT + ST_AND + ST_FECHA_INICIO);
                declaracionPreparada.setString(1, String.valueOf(inicio));
            }
            if (inicio == null && fin != null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_PERIODOS + ST_WHERE + ST_CONTRAT + ST_AND + ST_FECHA_FINAL);
                declaracionPreparada.setString(1, String.valueOf(fin));
            }
            resultado2 = declaracionPreparada.executeQuery();
            ControladorEmpleados co = new ControladorEmpleados();
            ArrayList<Integer> cui = new ArrayList<>();

            while (resultado2.next()) {
                cui.add(resultado2.getInt("Cui"));
            }
            ArrayList<Empleado> emp = new ArrayList<>();
            for (int i = 0; i < cui.size(); i++) {
                emp.add(co.obtenerEmpleado(cui.get(i)));

            }
            if (area != null) {
                for (int i = 0; i < emp.size(); i++) {
                    if (emp.get(i).getArea().getCodigo().equals(area.getCodigo())) {
                        String[] s = new String[5];
                        s[0] = String.valueOf(emp.get(i).getCui());
                        s[1] = emp.get(i).getNombre();
                        s[2] = "$" + String.valueOf(emp.get(i).getSalario().get(0).getMonto());
                        s[3] = String.valueOf(emp.get(i).getArea().getNombre());
                        s[4] = String.valueOf(emp.get(i).obtenerFechaContratado());
                        contratados.add(s);
                    }
                }
            } else {
                for (int i = 0; i < emp.size(); i++) {

                    String[] s = new String[5];
                    s[0] = String.valueOf(emp.get(i).getCui());
                    s[1] = emp.get(i).getNombre();
                    s[2] = "$" + String.valueOf(emp.get(i).getSalario().get(0).getMonto());
                    s[3] = String.valueOf(emp.get(i).getArea().getNombre());
                    s[4] = String.valueOf(emp.get(i).obtenerFechaContratado());
                    contratados.add(s);
                }
            }
        } catch (NumberFormatException | SQLException e) {
        }
        return contratados;
    }

    public ArrayList<String[]> obtenerRetirados(LocalDate inicio, LocalDate fin, Area area) {
        ArrayList<String[]> contratados = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            ResultSet resultado2 = null;
            PreparedStatement declaracionPreparada = null;
            if (inicio == null && fin == null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_PERIODOS + ST_WHERE + ST_RETIRADOS);
            }
            if (inicio != null && fin != null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_PERIODOS + ST_WHERE + ST_RETIRADOS + ST_AND + ST_FECHA_INICIO + ST_AND + ST_FECHA_FINAL);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                declaracionPreparada.setString(2, String.valueOf(fin));
            }
            if (inicio != null && fin == null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_PERIODOS + ST_WHERE + ST_RETIRADOS + ST_AND + ST_FECHA_INICIO);
                declaracionPreparada.setString(1, String.valueOf(inicio));
            }
            if (inicio == null && fin != null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_PERIODOS + ST_WHERE + ST_RETIRADOS + ST_AND + ST_FECHA_FINAL);
                declaracionPreparada.setString(1, String.valueOf(fin));
            }
            resultado2 = declaracionPreparada.executeQuery();
            ControladorEmpleados co = new ControladorEmpleados();
            ArrayList<Integer> cui = new ArrayList<>();

            while (resultado2.next()) {
                cui.add(resultado2.getInt("Cui"));
            }
            ArrayList<Empleado> emp = new ArrayList<>();
            for (int i = 0; i < cui.size(); i++) {
                emp.add(co.obtenerEmpleado(cui.get(i)));

            }
            if (area != null) {
                for (int i = 0; i < emp.size(); i++) {
                    if (emp.get(i).getArea().getCodigo().equals(area.getCodigo())) {
                        String[] s = new String[5];
                        s[0] = String.valueOf(emp.get(i).getCui());
                        s[1] = emp.get(i).getNombre();
                        s[2] = "$" + String.valueOf(emp.get(i).getSalario().get(0).getMonto());
                        s[3] = String.valueOf(emp.get(i).getArea().getNombre());
                        s[4] = String.valueOf(emp.get(i).obtenerFechaContratado());
                        contratados.add(s);
                    }
                }
            } else {
                for (int i = 0; i < emp.size(); i++) {

                    String[] s = new String[5];
                    s[0] = String.valueOf(emp.get(i).getCui());
                    s[1] = emp.get(i).getNombre();
                    s[2] = "$" + String.valueOf(emp.get(i).getSalario().get(0).getMonto());
                    s[3] = String.valueOf(emp.get(i).getArea().getNombre());
                    s[4] = String.valueOf(emp.get(i).obtenerFechaContratado());
                    contratados.add(s);
                }
            }
        } catch (NumberFormatException | SQLException e) {
        }
        return contratados;
    }

    public ArrayList<String[]> obtenerMedicamentos(String txt) {
        ArrayList<String[]> contratados = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            ControladorFarmacia con = new ControladorFarmacia();
            ArrayList<Tipo> tipos = con.obtenerTipos();
            for (int i = 0; i < tipos.size(); i++) {
                ArrayList<Medicina> me = con.obtenerMedicamentosPorTipo(tipos.get(i).getCodigo());
                String[] s = new String[7];
                if (txt != null && !"".equals(txt)) {
                    if (tipos.get(i).getNombre().contains(txt)) {
                        s[0] = String.valueOf(tipos.get(i).getCodigo());
                        s[1] = String.valueOf(tipos.get(i).getNombre());
                        s[2] = String.valueOf(tipos.get(i).getDescripcion());
                        s[3] = String.valueOf(me.size());
                        s[4] = String.valueOf(tipos.get(i).getCantidadMinima());
                        s[5] = String.valueOf(tipos.get(i).getPrecio());
                        if (me.isEmpty()) {
                             s[6] = String.valueOf(1);
                        }else{
                             s[6] = String.valueOf(me.get(0).getCompra().getCosto());
                        }
                        contratados.add(s);
                       
                    }
                } else {
                    s[0] = String.valueOf(tipos.get(i).getCodigo());
                    s[1] = String.valueOf(tipos.get(i).getNombre());
                    s[2] = String.valueOf(tipos.get(i).getDescripcion());
                    s[3] = String.valueOf(me.size());
                    s[4] = String.valueOf(tipos.get(i).getCantidadMinima());
                    s[5] = String.valueOf(tipos.get(i).getPrecio());
                    if (me.isEmpty()) {
                             s[6] = String.valueOf(1);
                        }else{
                             s[6] = String.valueOf(me.get(0).getCompra().getCosto());
                        }
                    contratados.add(s);
                }
                

            }
           
        } catch (NumberFormatException e) {
        }
        return contratados;
    }
    
    
     public ArrayList<MedicamentosDTO> obtenerVentas(LocalDate inicio,LocalDate fin ,String txt) {
        ArrayList<MedicamentosDTO> ventas = new ArrayList<>();
        
        ControladorEmpleados controladorEmpleados=new ControladorEmpleados();
        ArrayList<Empleado> farmaceuticos= controladorEmpleados.obtenerEmpleadosPorArea(Area.CONDIGO_FARMACEUTICO);
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            
            for (int i = 0; i < farmaceuticos.size(); i++) {
                ArrayList<Venta> ventasEmp=new ArrayList<>();
                
                
                
            }
            ControladorFarmacia con = new ControladorFarmacia();
            ArrayList<Tipo> tipos = con.obtenerTipos();
            for (int i = 0; i < tipos.size(); i++) {
                ArrayList<Medicina> me = con.obtenerMedicamentosPorTipo(tipos.get(i).getCodigo());
                String[] s = new String[7];
                if (txt != null && !"".equals(txt)) {
                    if (tipos.get(i).getNombre().contains(txt)) {
                        s[0] = String.valueOf(tipos.get(i).getCodigo());
                        s[1] = String.valueOf(tipos.get(i).getNombre());
                        s[2] = String.valueOf(tipos.get(i).getDescripcion());
                        s[3] = String.valueOf(me.size());
                        s[4] = String.valueOf(tipos.get(i).getCantidadMinima());
                        s[5] = String.valueOf(tipos.get(i).getPrecio());
                        if (me.isEmpty()) {
                             s[6] = String.valueOf(1);
                        }else{
                             s[6] = String.valueOf(me.get(0).getCompra().getCosto());
                        }
                        ventas.add(s);
                       
                    }
                } else {
                    s[0] = String.valueOf(tipos.get(i).getCodigo());
                    s[1] = String.valueOf(tipos.get(i).getNombre());
                    s[2] = String.valueOf(tipos.get(i).getDescripcion());
                    s[3] = String.valueOf(me.size());
                    s[4] = String.valueOf(tipos.get(i).getCantidadMinima());
                    s[5] = String.valueOf(tipos.get(i).getPrecio());
                    if (me.isEmpty()) {
                             s[6] = String.valueOf(1);
                        }else{
                             s[6] = String.valueOf(me.get(0).getCompra().getCosto());
                        }
                    ventas.add(s);
                }
                

            }
           
        } catch (NumberFormatException e) {
        }
        return ventas;
    }
    

}
