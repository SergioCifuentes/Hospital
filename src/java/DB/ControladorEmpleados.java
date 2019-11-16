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
import Empleados.Salario;
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
    private final static String ST_GUARDAR_EMPLEADO = "INSERT INTO Empleado VALUES(?,?,?,?,?)";
    private final static String ST_GUARDAR_PERIODO_LABORAL = "INSERT INTO PeriodoLaboral VALUES(?,?,?,?)";
    private final static String ST_GUARDAR_SUELDO = "INSERT INTO Sueldo VALUES(?,?,?)";
    private final static String ST_GUARDAR_USUARIO = "INSERT INTO Usuario VALUES(?,?,?)";
    private final static String ST_UPDATE_IGSS = "UPDATE Empleado SET ";
    private final static String ST_UPDATE_IGSS_2 = "=? WHERE Cui = ?";
private final static String ST_BORRAR_USUARIO = "DELETE FROM Usuario WHERE Cui = ?";
    private final static String ST_OBTENER_CUI_EMPLEADOS = "SELECT Cui FROM Empleado";
    private final static String ST_OBTENER_AREA = "SELECT * FROM Area WHERE Codigo = ?";
    private final static String ST_OBTENER_AREA_POR_NOMBRE = "SELECT Codigo FROM Area WHERE Nombre=?";
    private final static String ST_OBTENER_CODIGOS_AREAS = "SELECT Codigo FROM Area";
    private final static String ST_OBTENER_CODIGOS_PERIODOS = "SELECT Codigo FROM PeriodoLaboral";
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
                                empl.getArea(), empl.isIgss(), empl.isIrtra(), empl.getPeriodos(), userName, password);
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
                empleado = new Empleado(resultado2.getString("Nombre"), cui, obtenerArea(resultado2.getString("CodigoArea")), obtenerPeriodos(cui),
                        Boolean.valueOf(resultado2.getString("Igss")), Boolean.valueOf(resultado2.getString("Irtra")));
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

    public Area obtenerAreaPorNombre(String nombre) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_AREA_POR_NOMBRE);
            declaracionPreparada.setString(1, nombre);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                return obtenerArea(resultado2.getString("Codigo"));
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

    public ArrayList<Area> obtenerAreas() {
        ArrayList<Area> areas = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_CODIGOS_AREAS);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                areas.add(obtenerArea(resultado2.getString("Codigo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return areas;
    }

    public void guardarEmpleado(Empleado empleado) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_GUARDAR_EMPLEADO);
            declaracionPreparada.setString(1, String.valueOf(empleado.getCui()));
            declaracionPreparada.setString(2, String.valueOf(empleado.getArea().getCodigo()));
            declaracionPreparada.setString(3, String.valueOf(empleado.getNombre()));
            declaracionPreparada.setString(4, "0");
            declaracionPreparada.setString(5,"0");
            declaracionPreparada.execute();
            for (int i = 0; i < empleado.getPeriodos().size(); i++) {
                guardarPeriodo(empleado.getPeriodos().get(i), empleado.getCui());

            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPeriodo(PeriodoLaboral periodo, int cui) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_GUARDAR_PERIODO_LABORAL);
            declaracionPreparada.setString(2, String.valueOf(cui));
            declaracionPreparada.setString(1, String.valueOf(GeneradorDeCodigos.generarCodigoPeriodoLaboral()));
            declaracionPreparada.setString(3, String.valueOf(periodo.getNombre()));
            declaracionPreparada.setString(4, String.valueOf(periodo.getFecha()));
            declaracionPreparada.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList obtenerCodigosPeriodos() {
        ArrayList codigos = new ArrayList();
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_CODIGOS_PERIODOS);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                codigos.add(obtenerArea(resultado2.getString("Codigo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigos;
    }

    public void guardarSalario(Salario salario, int cui) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_GUARDAR_SUELDO);
            declaracionPreparada.setString(1, String.valueOf(cui));
            declaracionPreparada.setString(2, String.valueOf(salario.getMonto()));
            declaracionPreparada.setString(3, String.valueOf(salario.getFecha()));
            declaracionPreparada.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarIgssoIrtra(boolean igss, boolean irtra, int cui) {

        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_UPDATE_IGSS + "Igss" + ST_UPDATE_IGSS_2);
            declaracionPreparada.setString(2, String.valueOf(cui));
            if (igss) {
                declaracionPreparada.setString(1, String.valueOf("1"));
            } else {
                declaracionPreparada.setString(1, String.valueOf("0"));
            }

            declaracionPreparada.execute();
            declaracionPreparada = getConeccion().prepareStatement(ST_UPDATE_IGSS + "Irtra" + ST_UPDATE_IGSS_2);
            declaracionPreparada.setString(2, String.valueOf(cui));
            if (irtra) {
                declaracionPreparada.setString(1, String.valueOf("1"));
            } else {
                declaracionPreparada.setString(1, String.valueOf("0"));
            }
            declaracionPreparada.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarUsuario(String username, String password, int cui) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_GUARDAR_USUARIO);
            declaracionPreparada.setString(1, String.valueOf(cui));
            declaracionPreparada.setString(2, username);
            declaracionPreparada.setString(3, password);
            declaracionPreparada.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarUsuario(int cui) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_BORRAR_USUARIO);
            declaracionPreparada.setString(1, String.valueOf(cui));
            declaracionPreparada.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
