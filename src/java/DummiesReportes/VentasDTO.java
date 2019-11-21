/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DummiesReportes;

import Medicamento.Venta;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author sergio
 */
public class VentasDTO {
    
    private String cui;
    private String nombre;
    private JRBeanCollectionDataSource dataSource;
    private ArrayList<Venta> ventas;

    public VentasDTO(String cui, String nombre, ArrayList<Venta> list) {
        this.cui = cui;
        this.nombre = nombre;
        this.dataSource = new JRBeanCollectionDataSource(list);
        ventas=list;
    }

    public String getCui() {
        return cui;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public String getNombre() {
        return nombre;
    }

    public JRBeanCollectionDataSource getDataSource() {
        return dataSource;
    }
    
    
    
}
