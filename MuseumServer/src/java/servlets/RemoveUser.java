/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businessDomainObjects.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistance.DatabaseQueryExecutor;
import utility.InputValidator;
import utility.Redirector;

/**
 *
 * @author Alex
 */
public class RemoveUser extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userID = 0;
            String userIDString = request.getParameter("userID");

            if (userIDString != null) {
                userID = Integer.parseInt(InputValidator.clean(userIDString));
            }

            if (userIDString == null || userIDString.isEmpty()) {
                request.setAttribute("message", "<h2 style='color:red'>Please select a user</h2>");
                Redirector.redirect(request, response, "/admin/removeUserForm.jsp");
                return;
            }

            ServletContext ctx = request.getServletContext();
            UserManager um = (UserManager) ctx.getAttribute("userManager");

            if (um.removeUser(userID)) {
                request.setAttribute("message", "<h2>Successfully removed user <i>\"" + userID + "\"</i> from the database.</h2>");
                Redirector.redirect(request, response, "/admin/removeUserForm.jsp");
                return;
            } else {
                request.setAttribute("message", "<h2 style='color:red'>Failed to remove user <i>\"" + userID + "\"</i> from the database.</h2>");
                Redirector.redirect(request, response, "/admin/removeUserForm.jsp");
                return;
            }
        } finally {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
