<%-- 
    Document   : removeTour
    Created on : 28-Feb-2013, 14:43:12
    Author     : Darkstar
--%>

<%@page import="businessDomainObjects.Tour"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessDomainObjects.TourManager"%>
<%@page import="businessDomainObjects.UserManager"%>
<%@page import="persistance.PersistanceRepositoryTour"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Tour</title>
    </head>
    <body>
        <h1>Remove Tour</h1>
        <%
            UserManager um = (UserManager) request.getServletContext().getAttribute("userManager");
            TourManager tm = (TourManager) request.getServletContext().getAttribute("tourManager");
        %>
        <b>Select a Tour to Remove:</b><br />
        <form method="POST" action="RemoveTourAction.do">
        <select name="selectedTour">
            <%
                ArrayList<Tour> tourList = tm.getListOfTours();

                for(Tour t : tourList){
                    out.println("<option value=\"" + t.getTourID() + "\">" + t.getName() + "</option>");
                }
                
                
            %>
        </select>
        <%
            out.println("<input type=\"SUBMIT\" value=\"Remove Tour\" align=\"left\"></input><br />");
        %>
    </body>
</html>