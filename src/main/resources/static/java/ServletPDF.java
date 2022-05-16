/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ServletPDF", urlPatterns = {"/ServletPDF"})
public class ServletPDF extends HttpServlet {

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
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        HttpSession sesion = request.getSession();

        Usuario usuario = (Usuario) sesion.getAttribute("Usuario");
        String nombreTabla = (String) sesion.getAttribute("nombre");
        String[] camposFinales = (String[]) sesion.getAttribute("campos");
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = dtf.format(LocalDateTime.now());

        if (request.getParameter("pdf-" + nombreTabla) != null) {

            try {
                com.itextpdf.text.Document documento = new com.itextpdf.text.Document();
                PdfWriter.getInstance(documento, out);
                documento.open();
                
                Image foto = Image.getInstance("C:\\Users\\SENA\\Documents\\NetBeansProjects\\Prueba\\web\\imagenes\\digitalocean_logo.png");
                foto.scaleToFit(500, 200);
                foto.setAlignment(Chunk.ALIGN_MIDDLE);
                documento.add(foto);

                Paragraph parrafo = new Paragraph();
                Font titulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                parrafo.add(new Phrase("El Banco Intercontinental del Futuro", new Font(Font.FontFamily.HELVETICA, 14)));
                parrafo.add(new Phrase(Chunk.NEWLINE));
                parrafo.add(new Phrase(Chunk.NEWLINE));
                
                parrafo.add(new Phrase("LISTADO DE " + nombreTabla.toUpperCase(), titulo));
                parrafo.setAlignment(Element.ALIGN_CENTER);
                parrafo.add(new Phrase(Chunk.NEWLINE));
                parrafo.add(new Phrase(Chunk.NEWLINE));
                documento.add(parrafo);

                PdfPTable tabla = new PdfPTable(camposFinales.length);
                tabla.setHorizontalAlignment(0);
                tabla.setWidthPercentage(100);

                // Todos los campos de la tabla
                PdfPCell[] arrayCells = new PdfPCell[camposFinales.length];

                for (int i = 0; i < camposFinales.length; i++) {
                    arrayCells[i] = new PdfPCell(new Paragraph(camposFinales[i], FontFactory.getFont("arial", 12, Font.BOLD)));
                    tabla.addCell(arrayCells[i]);
                }

                DatosDAO dao = new DatosDAO();
                Tabla tablaConsulta = dao.consultarDatos(nombreTabla, camposFinales, usuario);
                ArrayList<String[]> valoresFinales = tablaConsulta.getValoresTotales();

                for (int i = 0; i < valoresFinales.size(); i++) {
                    String[] valores = valoresFinales.get(i);

                    for (int j = 0; j < valores.length; j++) {
                        tabla.addCell(valores[j]);
                    }
                }

                documento.add(tabla);
                
                parrafo = new Paragraph();
                parrafo.add(new Phrase(Chunk.NEWLINE));
                parrafo.add(new Phrase("Fecha de Generación: " + fechaActual, new Font(Font.FontFamily.HELVETICA, 11)));
                parrafo.add(new Phrase(Chunk.NEWLINE));
                parrafo.add(new Phrase("Generado por: " + usuario.getNombreReal(), new Font(Font.FontFamily.HELVETICA, 11)));
                parrafo.add(new Phrase(Chunk.NEWLINE));
                parrafo.add(new Phrase(usuario.getNombreUsuario(), new Font(Font.FontFamily.HELVETICA, 11)));
                parrafo.setAlignment(Element.ALIGN_CENTER);
                
                documento.add(parrafo);
                documento.close();

            } catch (Exception ex) {
                Logger.getLogger(ServletPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
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
