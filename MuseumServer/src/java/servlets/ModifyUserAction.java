package servlets;

import businessDomainObjects.User;
import businessDomainObjects.UserManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Simon
 */
public class ModifyUserAction extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        // Get a new HTTP session
        HttpSession session = request.getSession();
                
        UserManager um = (UserManager) request.getServletContext().getAttribute("userManager");
        
        // Username check and pass through
        String username = request.getParameter("username");
        String password = request.getParameter("password");  
        User user = um.validateUser(username, password);
        
        session.setAttribute("currentUser", user);
        request.setAttribute("user", user);
        
        // Get user ID, set to request
        ArrayList<Integer> userIDList = (ArrayList<Integer>) request.getServletContext().getAttribute("userID");
        
        String userName, userPassword;
        int userID;
        
        
        // Get form parameters
        //Checkbox, retrieve parameter, update the appropriate SQL field
        userID = Integer.parseInt(request.getParameter("userToModify"));
        
        //userName
        userName = request.getParameter("userName");
        
        //userPassword, remembering to md5 first
        userPassword = request.getParameter("password");
        
        // SQL update statement
        
        
        // If nothing was entered into one of the required form elements
        if(userName.equals("") || userPassword.equals("")){ //FIXME: Null check needs fixing for all incl userID
            RequestDispatcher view = request.getRequestDispatcher("modifyUser.jsp");
            view.forward(request, response);
        }
        else{
            // Instantiate a request dispatcher for the JSP
            RequestDispatcher view = request.getRequestDispatcher("modifyConfirm.jsp");
            view.forward(request, response);
        }
        
        // Use the request dispatcher to ask the Container to crank up the JSP,
        // sending it the request and response

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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ModifyUserAction.class.getName()).log(Level.SEVERE, null, ex);
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