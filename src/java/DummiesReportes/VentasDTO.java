/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DummiesReportes;

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

    public VentasDTO(String cui, String nombre, List list) {
        this.cui = cui;
        this.nombre = nombre;
        this.dataSource = new JRBeanCollectionDataSource(list);
    }

    public String getCui() {
        return cui;
    }

    public String getNombre() {
        return nombre;
    }

    public JRBeanCollectionDataSource getDataSource() {
        return dataSource;
    }
    
    
    
}
