/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import modelo.DatosDAO;
import modelo.Tabla;
import modelo.Usuario;

/**
 *
 * @author SENA
 */
@WebServlet(name = "ServletOperaciones", urlPatterns = {"/ServletOperaciones"})
public class ServletOperaciones extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        DataMethods data = new DataMethods();
        OpMethods operations = new OpMethods();
        HttpSession sesion = request.getSession();

        Usuario user = (Usuario) sesion.getAttribute("Usuario");
        String nombreTabla = (String) sesion.getAttribute("nombre");
        String[] campos = (String[]) sesion.getAttribute("campos");
        ArrayList<String[]> valores = data.recolectarDatos(campos, request, response);

        DatosDAO dao = new DatosDAO();

        if (request.getParameter("btn-main-" + nombreTabla) != null) {

            String[] valoresFinales = valores.get(1);
            String saldo = dao.comprobarDatosPorId("cuentas", new String[]{"CodCuenta", "Saldo"}, valoresFinales[1])[0];

            if (saldo != null) {
                Integer saldoInt = Integer.parseInt(saldo);
                
                if (saldoInt > 0) {
                    if (operations.realizarOperacion(saldo, valoresFinales)) {
                        JOptionPane.showMessageDialog(null, "La operación se ha realizado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Hubo un fallo inesperado");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No hay saldo disponible");
                }
            }

        } else if (request.getParameter("btn-update-" + nombreTabla) != null) {

            String[] camposFinales = valores.get(0);
            String[] valoresFinales = valores.get(1);
            Tabla tabla = new Tabla(nombreTabla, camposFinales, valoresFinales);

            if (dao.actualizarDato(tabla.getNombreTabla(), tabla.getCamposTabla(), tabla.getValoresTabla())) {
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
            }

        } else if (request.getParameter("btn-delete-" + nombreTabla) != null) {

            String campoPrincipal = campos[0];
            String codigo = request.getParameter(campoPrincipal);

            if (dao.borrarDato(nombreTabla, campoPrincipal, codigo)) {
                JOptionPane.showMessageDialog(null, "Datos Eliminados");
            }

        }

        response.sendRedirect(nombreTabla + ".jsp");

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
        processRequest(request, response);
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
