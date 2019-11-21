/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlJasper;

import DummiesReportes.ContratadosDTO;
import DummiesReportes.MedicamentosDTO;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 *
 * @author sergio
 */
public class ReporteEmpleados {

    public void imprimir() throws IOException {
        File ob = new File("ReporteEmpleados.pdf");
        Desktop.getDesktop().open(ob);
    }

    public void imprimirReporteContratados(ArrayList<ContratadosDTO> contratadosDTOs) throws JRException {
        JasperPrint jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/ControlJasper/Silhouette_Landscape.jasper"), null, new JRBeanCollectionDataSource(contratadosDTOs));
        JRPdfExporter exp = new JRPdfExporter();

        exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteEmpleados.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }

    public void imprimirReporteRetirados(ArrayList<ContratadosDTO> contratadosDTOs) throws JRException {
        JasperPrint jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/ControlJasper/Retirados.jasper"), null, new JRBeanCollectionDataSource(contratadosDTOs));
        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteEmpleados.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }

    public void imprimirReporteMedicamento(ArrayList<MedicamentosDTO> medicamentosDTO) throws JRException {
        JasperPrint jasperPrint2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/ControlJasper/Medicamentos.jasper"), null, new JRBeanCollectionDataSource(medicamentosDTO));
        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint2));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteEmpleados.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }
}
