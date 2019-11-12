/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Empleados.Area;
import Empleados.Areas.Contratador;
import Empleados.Empleado;
import Empleados.PeriodoLaboral;
import Empleados.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class ControladorEmpleados extends Coneccion {

    private final static String ST_USERNAME = "SELECT * FROM Usuario WHERE UserName = ?";
    private final static String ST_OBTENER_USUARIO = "SELECT * FROM Usuario WHERE UserName = ? AND Password= ?";
    private final static String ST_OBTENER_EMPLEADO = "SELECT * FROM Empleado WHERE Cui = ?";
    private final static String ST_OBTENER_CUI_EMPLEADOS = "SELECT Cui FROM Empleado";
    private final static String ST_OBTENER_AREA = "SELECT * FROM Area WHERE Codigo = ?";
    private final static String ST_OBTENER_PERIODO_LABORAL = "SELECT * FROM PeriodoLaboral WHERE Cui = ? ORDER BY Fecha DESC;";

    public boolean verificarUserName(String userName) {
        PreparedStatement declaracionPreparada = null;
        ResultSet resultado2 = null;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            declaracionPreparada = getConeccion().prepareStatement(ST_USERNAME);
            declaracionPreparada.setString(1, userName);
            resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Usuario obtenerUsuario(String userName, String password) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_USUARIO);
            declaracionPreparada.setString(1, userName);
            declaracionPreparada.setString(2, password);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                Empleado empl = obtenerEmpleado(resultado2.getInt("Cui"));
                switch (empl.getArea().getCodigo()) {
                    case Area.CONDIGO_CONTRATADOR:
                        return new Contratador(empl.getNombre(), empl.getCui(),
                                 empl.getArea(), empl.getPeriodos(), userName, password);
                    default:
                        throw new AssertionError();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Empleado obtenerEmpleado(int cui) {
        Empleado empleado = null;
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_EMPLEADO);
            declaracionPreparada.setString(1, String.valueOf(cui));
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                empleado = new Empleado(resultado2.getString("Nombre"), cui, obtenerArea(resultado2.getString("CodigoArea")), obtenerPeriodos(cui));
                return empleado;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Empleado> obtenerEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_CUI_EMPLEADOS);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                empleados.add(obtenerEmpleado(resultado2.getInt("Cui")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleados;
    }

    public ArrayList<Empleado> obtenerPorPeriodoLaboral(String periodo) {
        ArrayList<Empleado> empleados = obtenerEmpleados();
        ArrayList<Empleado> empleadosPorPeriodo = new ArrayList<>();
        
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getPeriodos().get(0).getNombre().equals(periodo)) {
                empleadosPorPeriodo.add(empleados.get(i));
            }

        }

        return empleadosPorPeriodo;
    }

    public Area obtenerArea(String codigo) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_AREA);
            declaracionPreparada.setString(1, codigo);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                return new Area(resultado2.getNString("Nombre"), codigo, resultado2.getBoolean("Usuario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<PeriodoLaboral> obtenerPeriodos(int cui) {
        ArrayList<PeriodoLaboral> periodoLaborals = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_PERIODO_LABORAL);
            declaracionPreparada.setString(1, String.valueOf(cui));
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                periodoLaborals.add(new PeriodoLaboral(resultado2.getString("Nombre"), resultado2.getObject("Fecha", LocalDate.class)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return periodoLaborals;
    }
}
