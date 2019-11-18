/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webService;

import DAO.DAOException;
import DAO.DiscountCodeDAO;
import data.DataSourceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author marie
 */
@WebServlet(name = "DeleteCodeServlet", urlPatterns = {"/DeleteCodeServlet"})
public class DeleteCodeServlet extends HttpServlet {
    
    private DiscountCodeDAO dao; 
    private DataSource myDataSource;
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
        
        myDataSource = DataSourceFactory.getDataSource();
        DiscountCodeDAO dao = new DiscountCodeDAO(DataSourceFactory.getDataSource());
        String code = request.getParameter("code");
        String msg = "";
        
        try{
            int rep = dao.deleteCode(code);
            if (rep == 0) {
               throw new DAOException("code "+code+" -> Supression impossible -> rep "+rep);
            }
            msg = "Le code "+code+" a été suprimé.";
        }
        catch(DAOException e){
            msg = e.getMessage();
        }
        
        try (PrintWriter out = response.getWriter()) {
                out.println(msg);
        }
        catch(Exception e){} 
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
