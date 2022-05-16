/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
 * @author usuario
 */
@MultipartConfig
@WebServlet(name = "ServletPerfil", urlPatterns = {"/ServletPerfil"})
public class ServletPerfil extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        DataMethods data = new DataMethods();
        
        HttpSession sesion = request.getSession();
        
        String nombreTabla = (String) sesion.getAttribute("nombre");
        String[] nombreTablas = nombreTabla.split("-");
        
        ArrayList<String[]> campos = new ArrayList<>();
        campos.add((String[]) sesion.getAttribute("camposCliente"));
        campos.add((String[]) sesion.getAttribute("camposUsuario"));
        
        Usuario user = (Usuario) sesion.getAttribute("Usuario");
        
        ArrayList<String[]> valoresUsuario = data.recolectarDatos(campos.get(1), request, response);
        ArrayList<String[]> valoresCliente = data.recolectarDatos(campos.get(0), request, response);
        
        DatosDAO dao = new DatosDAO();
        UtilitiesPassword util = new UtilitiesPassword();

        if (request.getParameter("btn-update-" + nombreTabla) != null) {
            ArrayList<String[]> valores = new ArrayList<>();
            valores.add(valoresCliente.get(1));
            valores.add(valoresUsuario.get(1));
            
            ArrayList<String[]> camposFinales = new ArrayList<>();
            camposFinales.add(valoresCliente.get(0));
            camposFinales.add(valoresUsuario.get(0));

            String codigo = user.getDocumento();
            String nombre = request.getParameter("NomUsu");
            String clave = request.getParameter("Clave");
            
            if (clave != null && !clave.equals("")) {
                clave = util.encriptarPass(clave);
            }
            
            if (dao.actualizarPerfil(nombreTablas, user.getDocumento(), camposFinales, valores)) {
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
                
                if (codigo.equals(user.getDocumento())) {
                    if (nombre == null || nombre.equals("")) {
                        nombre = user.getNombreUsuario();
                    }
                    if (clave == null || clave.equals("")) {
                        clave = user.getClave();
                    }
                    
                    Usuario userUpdated = dao.comprobarLogin(nombre, clave);
                    sesion.setAttribute("Usuario", userUpdated);
                }
            }
            
        }
        
        response.sendRedirect("perfil.jsp");
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


