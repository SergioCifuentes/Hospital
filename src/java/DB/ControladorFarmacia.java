/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Empleados.PeriodoLaboral;
import Medicamento.Compra;
import Medicamento.Medicina;
import Medicamento.Tipo;
import Medicamento.Venta;
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
public class ControladorFarmacia extends Coneccion {

    private final static String ST_OBTENER_TIPOS = "SELECT * FROM TipoMedicamento WHERE Codigo=?";
    private final static String ST_OBTENER_TIPOS_POR_NOMBRE = "SELECT * FROM TipoMedicamento WHERE Nombre=?";
    private final static String ST_OBTENER_MEDICAMENTO_POR_CODIGO = "SELECT * FROM Medicamento WHERE Codigo=?";
    private final static String ST_OBTENER_COMPRA_POR_CODIGO = "SELECT * FROM Compra WHERE CodigoMedicamento=?";
    private final static String ST_GUARDAR_MEDICINA = "INSERT INTO Medicamento VALUES(?,?)";
    private final static String ST_GUARDAR_VENTA = "INSERT INTO Venta VALUES(?,?,?)";
    private final static String ST_GUARDAR_TIPO_MEDICINA = "INSERT INTO TipoMedicamento VALUES(?,?,?,?,?)";
    private final static String ST_GUARDAR_COMPRA = "INSERT INTO Compra VALUES(?,?,?)";
    private final static String ST_OBTENER_CODIGO_TIPOS = "SELECT Codigo FROM TipoMedicamento ";
    private final static String ST_OBTENER_MEDICAMENTO = "SELECT Codigo FROM Medicamento ";
    private final static String ST_OBTENER_MEDICAMENTO_POR_TIPO = "SELECT Codigo FROM Medicamento WHERE CodigoTipo= ? ";
    private final static String ST_OBTENER_VENTA_POR_MEDICAMENTO = "SELECT * FROM Venta WHERE CodigoMedicamento= ? ";
    private final static String ST_OBTENER_CODIGOS_MEDICAMENTO = "SELECT Codigo FROM Medicamento";

    public Tipo obtenerTipoPorCodigo(String codigo) {
        Tipo tipo = null;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_TIPOS);
            declaracionPreparada.setString(1, codigo);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                tipo = new Tipo(codigo, resultado2.getString("Nombre"), resultado2.getString("Descripcion"), resultado2.getFloat("Precio"),
                        resultado2.getInt("CantidadMinima"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipo;
    }

    public ArrayList<Tipo> obtenerTipos() {
        ArrayList<Tipo> tipos = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_CODIGO_TIPOS);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                tipos.add(obtenerTipoPorCodigo(resultado2.getString("Codigo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipos;
    }

    public Tipo obtenerTiposPorNombre(String nombre) {
        Tipo tipos = null;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_TIPOS_POR_NOMBRE);
            declaracionPreparada.setString(1, nombre);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                tipos = (obtenerTipoPorCodigo(resultado2.getString("Codigo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipos;
    }

    public void GuardarMedicina(Medicina medicina) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            String codigo = GeneradorDeCodigos.generarCodigoMedicamento();
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_GUARDAR_MEDICINA);
            declaracionPreparada.setString(1, codigo);
            declaracionPreparada.setString(2, medicina.getTipo().getCodigo());

            declaracionPreparada.execute();
            GuardarCompra(codigo, medicina.getCompra());

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void GuardarVenta(Venta venta,Tipo medicamento) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_GUARDAR_VENTA);
           if (medicamento!=null) {
                ArrayList<Medicina> med = obtenerMedicamentosPorTipo(medicamento.getCodigo());
                 declaracionPreparada.setString(1, med.get(0).getCodigo());
            
            }else{
               declaracionPreparada.setString(1, venta.getMedicina().getCodigo());
            
            }
             declaracionPreparada.setString(2, String.valueOf(venta.getEmpleado().getCui())); 
            declaracionPreparada.setString(3, String.valueOf(venta.getFecha()));
     
            declaracionPreparada.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GuardarTipoMedicina(Tipo tipo) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            String codigo = GeneradorDeCodigos.generarCodigoTipoMedicamento();
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_GUARDAR_TIPO_MEDICINA);
            declaracionPreparada.setString(1, codigo);
            declaracionPreparada.setString(2, String.valueOf(tipo.getNombre()));
            declaracionPreparada.setString(3, String.valueOf(tipo.getDescripcion()));
            declaracionPreparada.setString(4, String.valueOf(tipo.getPrecio()));
            declaracionPreparada.setString(5, String.valueOf(tipo.getCantidadMinima()));

            declaracionPreparada.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GuardarCompra(String codigo, Compra compra) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_GUARDAR_COMPRA);
            declaracionPreparada.setString(1, codigo);
            declaracionPreparada.setString(2, String.valueOf(compra.getCosto()));
            declaracionPreparada.setString(3, String.valueOf(compra.getFecha()));

            declaracionPreparada.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList obtenerCodigosMedicamento() {
        ArrayList codigos = new ArrayList();
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_CODIGOS_MEDICAMENTO);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                codigos.add(resultado2.getString("Codigo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigos;
    }

    public ArrayList<Medicina> obtenerMedicamentos() {
        ArrayList<Medicina> medicima = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_MEDICAMENTO);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                medicima.add(obtenerMedicina(resultado2.getString("Codigo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicima;
    }

    
    
    public ArrayList<Medicina> obtenerMedicamentosPorTipo(String codigoTipo) {
        ArrayList<Medicina> medicima = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_MEDICAMENTO_POR_TIPO);
            declaracionPreparada.setString(1, codigoTipo);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                if (obtenerVentaDeMedicamento(resultado2.getString("Codigo"))==null) {
                    medicima.add(obtenerMedicina(resultado2.getString("Codigo")));
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicima;
    }
    
    
    public Venta obtenerVentaDeMedicamento(String codigoMedicina){
        Venta venta =null;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_VENTA_POR_MEDICAMENTO);
            declaracionPreparada.setString(1, codigoMedicina);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.next()) {
                ControladorEmpleados co =new ControladorEmpleados();
                venta= new Venta(co.obtenerEmpleado(Integer.parseInt(resultado2.getString("CuiVendedor")))
                        , obtenerMedicina(codigoMedicina), resultado2.getObject("Fecha", LocalDate.class));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return venta;
    }
    
    

    public Medicina obtenerMedicina(String codigo) {
        Medicina medicinas = null;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_MEDICAMENTO_POR_CODIGO);
            declaracionPreparada.setString(1, codigo);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                medicinas = new Medicina(codigo, obtenerTipoPorCodigo(resultado2.getString("CodigoTipo")), obtenerCompra(codigo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicinas;
    }

    public Compra obtenerCompra(String codigoMedicina) {
        Compra compra = null;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_COMPRA_POR_CODIGO);
            declaracionPreparada.setString(1, codigoMedicina);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                compra = new Compra(resultado2.getFloat("Costo"), resultado2.getObject("Fecha", LocalDate.class));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compra;
    }

}
