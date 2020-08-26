/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entidad.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "agregarCategoria", urlPatterns = {"/agregarCategoria"})
public class agregarCategoria extends HttpServlet {

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
        try {
            utx.begin();
        } catch (Exception ex) {
            Logger.getLogger(agregarDesarrolladora.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nombre = request.getParameter("categoriaNombre");
        if(em.createNamedQuery("Categoria.findByNombreCategoria").setParameter("nombreCategoria", nombre).getResultList().isEmpty()){
            Categoria catIngresar= new Categoria(em.createNamedQuery("Categoria.findAll").getResultList().size()+1, nombre);
            em.persist(catIngresar);
        }
        try {
            utx.commit();
            response.sendRedirect("adminJuegos.html");
        } catch (Exception ex) {
            try {
                utx.rollback();
                response.sendRedirect("IngresarDesarrolladora.html");
            } catch (Exception ex1) {
                Logger.getLogger(agregarDesarrolladora.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
