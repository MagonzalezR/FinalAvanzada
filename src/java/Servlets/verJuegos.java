/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entidad.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kil_5
 */
@WebServlet(name = "verJuegos", urlPatterns = {"/verJuegos"})
public class verJuegos extends HttpServlet {

    @PersistenceContext(unitName = "Final1PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

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
        try {
            utx.begin();
        } catch (Exception ex) {
            Logger.getLogger(agregarDesarrolladora.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Juego> mostrarJuego = em.createNamedQuery("Juego.findAll",Juego.class).getResultList();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Juegos disponibles</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>Bienvenido a la seleccion de juegos</h5><br><br>");
            for(Juego juego:mostrarJuego){
                Collection<Copia> mostrarCopia= juego.getCopiaCollection();
                out.println("Nombre: "+juego.getNombreJuego()+"<br>\n");
                out.println("Desarrollado por: "+juego.getDesarrolladoridDesarrollador().getNombreDesarrollador()+"<br><\n");
                out.println("Categorias a las que pertenece: ");
                for(Categoria categoria: juego.getCategoriaCollection()){
                    out.println(categoria.getNombreCategoria()+", ");
                }
                out.println("<br>Formatos disponibles: ");
                for(Copia copia:mostrarCopia){
                    for(Formato formato:copia.getFormatoCollection()){
                        out.println(formato.getNombreFormato()+", ");
                    }
                }
                out.println("<br><br>");
            }
            out.println("</body>");
            out.println("</html>");
        }
        try {
            utx.commit();
            response.sendRedirect("adminJuegos.html");
        } catch (Exception ex) {
            Logger.getLogger(agregarDesarrolladora.class.getName()).log(Level.SEVERE, null, ex);
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

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
