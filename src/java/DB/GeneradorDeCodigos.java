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
    private static final String ID_MEDICINA = "M";
    private static final String ID_TIPO_MEDICINA = "T";

    public static String generarCodigoPeriodoLaboral() {
        String codigo = "1000";
        ControladorEmpleados co = new ControladorEmpleados();
        codigo = String.valueOf(Integer.valueOf(codigo) + co.obtenerCodigosPeriodos().size());

        return ID_PERDIODO + codigo;
    }

    public static String generarCodigoMedicamento() {
        String codigo = "1000";
        ControladorFarmacia co = new ControladorFarmacia();
        codigo = String.valueOf(Integer.valueOf(codigo) + co.obtenerCodigosMedicamento().size());

        return ID_MEDICINA + codigo;
    }

    public static String generarCodigoTipoMedicamento() {
        String codigo = "1000";
        ControladorFarmacia co = new ControladorFarmacia();
        codigo = String.valueOf(Integer.valueOf(codigo) + co.obtenerTipos().size());

        return ID_TIPO_MEDICINA + codigo;
    }
}
