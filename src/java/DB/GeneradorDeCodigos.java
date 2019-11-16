/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/**
 *
 * @author sergio
 */
public class GeneradorDeCodigos {
    
    private static final String ID_PERDIODO = "P";
    
    
    public static String generarCodigoPeriodoLaboral(){
        String codigo="1000";
        ControladorEmpleados co = new ControladorEmpleados();
            codigo=String.valueOf(Integer.valueOf(codigo) + co.obtenerCodigosPeriodos().size());
        
        return ID_PERDIODO+codigo;
    }
}
