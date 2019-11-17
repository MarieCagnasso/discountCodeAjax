/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webService;

import DAO.DAOException;
import data.DataSourceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import data.DataSourceFactory;

import DAO.DiscountCodeDAO;
import model.DiscountCodeEntity;
import static java.lang.System.console;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.*;
import java.util.Collections;

/**
 *
 * @author marie
 */
@WebServlet(name = "ShowCode", urlPatterns = {"/ShowCode"})
public class ShowCodeServlet extends HttpServlet {

    private DiscountCodeDAO dao; // L'objet à tester
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
            throws ServletException, IOException, DAOException {
        myDataSource = DataSourceFactory.getDataSource();
        DiscountCodeDAO dao = new DiscountCodeDAO(DataSourceFactory.getDataSource());
        
        List<DiscountCodeEntity> codes = dao.showAllCode();
        
        
        // Print JSON
        try (PrintWriter out = response.getWriter()) {
                // On spécifie que la servlet va générer du JSON
                response.setContentType("application/json;charset=UTF-8");
               
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String gsonData = gson.toJson(codes);
                out.println(gsonData);
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
        
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(ShowCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
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
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(ShowCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
