/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entidad.Categoria;
import entidad.Desarrollador;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author kil_5
 */
@WebServlet(name = "juegos", urlPatterns = {"/juegos"})
public class juegos extends HttpServlet {

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
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(juegos.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Categoria> catLista = em.createNamedQuery("Categoria.findAll", Categoria.class).getResultList();
        List<Desarrollador> desLista = em.createNamedQuery("Desarrollador.findAll", Desarrollador.class).getResultList();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ingresar juego</title>");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link href=\"css/vistaJuegos.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h4>Bienvenido al agregador de juegos.</h4>\n");
            out.println("<form name=\"juego\" action=\"agregarJuego\" method=\"POST\">\n");
            out.println("<br>Nombre del juego  \n");
            out.println("<input type=\"text\" name=\"juegoNombre\" value=\"Nombre...\" /><br><br>\n");
            out.println("Categoria:<br>\n");
            for (Categoria categoria : catLista) {
                out.println("<input type=\"checkbox\" name=\"genero\" value=\"" + categoria.getNombreCategoria() + "\"> " + categoria.getNombreCategoria() + "<br>");
            }
            out.println("<br>Desarrolladora<br>\n");
            out.println("<select name=\"desarrolladora\">");
            for (Desarrollador desarrollador : desLista) {
                out.println("<option value=\"" + desarrollador.getNombreDesarrollador() + "\"> " + desarrollador.getNombreDesarrollador() + "</option>");
            }
            out.println("</select><br><br>");
            out.println("Plataforma<br>\n");
            out.println("<input type=\"checkbox\" name=\"plataforma\" value=\"PC\" />PC<br>\n");
            out.println("<input type=\"checkbox\" name=\"plataforma\" value=\"Play station 4\" />Play station 4<br>\n");
            out.println("<input type=\"checkbox\" name=\"plataforma\" value=\"Play station 3\" />Play station 3<br>\n");
            out.println("<input type=\"checkbox\" name=\"plataforma\" value=\"Xbox one\" />Xbox one<br>\n");
            out.println("<input type=\"checkbox\" name=\"plataforma\" value=\"Xbox 360\" />Xbox 360<br>\n");
            out.println("<input type=\"checkbox\" name=\"plataforma\" value=\"Nintendo Switch\" />Nintendo switch<br><br>");
            out.println("<input type=\"submit\" value=\"Enviar\">");
            out.println("</form><br><br>");
            out.println("<form name=\"volver\" action=\"adminJuegos.html\" method=\"POST\">\n");
            out.println("<input type=\"submit\" value=\"Volver\" name=\"botVolver\" />\n");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
        try {
            utx.commit();
        } catch (Exception ex) {
            Logger.getLogger(juegos.class.getName()).log(Level.SEVERE, null, ex);
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
