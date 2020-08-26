/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entidad.Categoria;
import entidad.Copia;
import entidad.Desarrollador;
import entidad.Formato;
import entidad.Juego;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

/**
 *
 * @author kil_5
 */
@WebServlet(name = "agregarJuego", urlPatterns = {"/agregarJuego"})
public class agregarJuego extends HttpServlet {

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
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(agregarJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nombre = request.getParameter("juegoNombre");
        String desarrolladora = request.getParameter("desarrolladora");
        String[] plataformas = request.getParameterValues("plataforma");
        String[] generos = request.getParameterValues("genero");
        Juego nuevoJuego = new Juego(em.createNamedQuery("Juego.findAll").getResultList().size() + 1, nombre);
        nuevoJuego.setCategoriaCollection(new ArrayList<>());
        nuevoJuego.setCopiaCollection(new ArrayList<>());
        Desarrollador desarrolladoraNueva = em.createNamedQuery("Desarrollador.findByNombreDesarrollador", Desarrollador.class).setParameter("nombreDesarrollador", desarrolladora).getSingleResult();
        nuevoJuego.setDesarrolladoridDesarrollador(desarrolladoraNueva);
        em.persist(nuevoJuego);
        for (String categoria : generos) {
            Categoria introducir = em.createNamedQuery("Categoria.findByNombreCategoria", Categoria.class).setParameter("nombreCategoria", categoria).getSingleResult();
            nuevoJuego.getCategoriaCollection().add(introducir);
        }
        for (String formato : plataformas) {
            Copia copia = new Copia(em.createNamedQuery("Copia.findAll").getResultList().size() + 1);
            copia.setFormatoCollection(new ArrayList<>());
            System.out.println(formato);
            Formato formatoAdd = em.createNamedQuery("Formato.findByNombreFormato", Formato.class).setParameter("nombreFormato", formato).getSingleResult();
            copia.getFormatoCollection().add(formatoAdd);
            formatoAdd.getCopiaCollection().add(copia);
            copia.setJuegoidJuego(nuevoJuego);
            em.persist(copia);
            nuevoJuego.getCopiaCollection().add(copia);
        }
        try {
            utx.commit();
            response.sendRedirect("adminJuegos.html");
        } catch (Exception e) {
            try {
                utx.rollback();
                response.sendRedirect("juegos");
            } catch (Exception ex) {
                Logger.getLogger(agregarJuego.class.getName()).log(Level.SEVERE, null, ex);
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
