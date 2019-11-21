/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DB.ControladorEmpleados;
import DB.ControladorReportes;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
public class FiltracionEmpleados extends HttpServlet {

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
            out.println("<title>Servlet FiltracionEmpleados</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FiltracionEmpleados at " + request.getContextPath() + "</h1>");
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
        if ("Filtrar".equals(request.getParameter("FiltrarCon"))) {
            LocalDate inicio;
            LocalDate fin;
            if (!"".equals(request.getParameter("inicio"))) {
                inicio = LocalDate.parse(request.getParameter("inicio"));
            } else {
                inicio = null;
            }
            if (!"".equals(request.getParameter("fin"))) {
                fin = LocalDate.parse(request.getParameter("fin"));
            } else {
                fin = null;
            }
            ControladorReportes co = new ControladorReportes();
            
            if ("Ninguna".equals(request.getParameter("Area"))) {
                request.setAttribute("EmpleadosAMostrarC", co.obtenerContratados(inicio, fin, null));
            } else {
                ControladorEmpleados con = new ControladorEmpleados();
                request.setAttribute("EmpleadosAMostrarC", co.obtenerContratados(inicio, fin, con.obtenerAreaPorNombre(request.getParameter("Area"))));

            }
            request.setAttribute("comentarios", true);
            request.setAttribute("Ganancias", false);
            request.setAttribute("inicioC", inicio);
            request.setAttribute("finC", fin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Contratador/Home.jsp");
            dispatcher.forward(request, response);
        }if ("Resetear".equals(request.getParameter("ResetearCon"))) {
            request.setAttribute("comentarios", true);
            request.setAttribute("Ganancias", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Contratador/Home.jsp");

            dispatcher.forward(request, response);
        }
        
        
         if ("Filtrar".equals(request.getParameter("FiltrarRet"))) {
            LocalDate inicio;
            LocalDate fin;
            if (!"".equals(request.getParameter("inicio"))) {
                inicio = LocalDate.parse(request.getParameter("inicio"));
            } else {
                inicio = null;
            }
            if (!"".equals(request.getParameter("fin"))) {
                fin = LocalDate.parse(request.getParameter("fin"));
            } else {
                fin = null;
            }
            ControladorReportes co = new ControladorReportes();
            
            if ("Ninguna".equals(request.getParameter("Area"))) {
                request.setAttribute("EmpleadosAMostrarRet", co.obtenerRetirados(inicio, fin, null));
            } else {
                ControladorEmpleados con = new ControladorEmpleados();
                request.setAttribute("EmpleadosAMostrarRet", co.obtenerRetirados(inicio, fin, con.obtenerAreaPorNombre(request.getParameter("Area"))));

            }
            request.setAttribute("comentarios", true);
            request.setAttribute("Ganancias", false);
            request.setAttribute("inicioC", inicio);
            request.setAttribute("finC", fin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Contratador/Home.jsp");
            dispatcher.forward(request, response);
        }if ("Resetear".equals(request.getParameter("ResetearRet"))) {
            request.setAttribute("comentarios", true);
            request.setAttribute("Ganancias", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Contratador/Home.jsp");

            dispatcher.forward(request, response);
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
