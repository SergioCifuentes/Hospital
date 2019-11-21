/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControlJasper.ReporteEmpleados;
import DummiesReportes.ContratadosDTO;
import DummiesReportes.MedicamentosDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author sergio
 */
public class Reportes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Reportes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Reportes at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<String[]> co = (ArrayList) session.getAttribute("Array");

        if ("ImportarPDF".equals(request.getParameter("Contratados"))) {
            ArrayList<ContratadosDTO> contDto = new ArrayList<>();

            for (int i = 0; i < co.size(); i++) {
                String salario = co.get(i)[2].substring(1);
                contDto.add(new ContratadosDTO(Integer.valueOf(co.get(i)[0]), co.get(i)[1],
                        Float.valueOf(salario), co.get(i)[3], co.get(i)[4]));
            }
            ReporteEmpleados re = new ReporteEmpleados();
            try {
                re.imprimirReporteContratados(contDto);
                re.imprimir();
            } catch (JRException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            getServletContext().getRequestDispatcher("/Contratador/Home.jsp").forward(request, response);

        } else if ("ImportarPDF".equals(request.getParameter("Retirados"))) {
            ArrayList<ContratadosDTO> contDto = new ArrayList<>();
            for (int i = 0; i < co.size(); i++) {

                String salario = co.get(i)[2].substring(1);
                contDto.add(new ContratadosDTO(Integer.valueOf(co.get(i)[0]), co.get(i)[1],
                        Float.valueOf(salario), co.get(i)[3], co.get(i)[4]));
            }
            ReporteEmpleados re = new ReporteEmpleados();
            try {
                re.imprimirReporteRetirados(contDto);
                re.imprimir();
            } catch (JRException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            getServletContext().getRequestDispatcher("/Contratador/Home.jsp").forward(request, response);

        } else if ("ImportarPDF".equals(request.getParameter("Medicamentos"))) {
            
            ArrayList<MedicamentosDTO> lista = new ArrayList<>();
            for (int i = 0; i < co.size(); i++) {
                lista.add(new MedicamentosDTO(co.get(i)[0], co.get(i)[1],
                        co.get(i)[2], co.get(i)[3], co.get(i)[4], co.get(i)[5], co.get(i)[6]));
            }
            ReporteEmpleados re = new ReporteEmpleados();
            try {
                re.imprimirReporteMedicamento(lista);
                re.imprimir();
            } catch (JRException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
            getServletContext().getRequestDispatcher("/Farmacia/HomeFarmacia.jsp").forward(request, response);

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
