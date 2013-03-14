<%-- 
    Document   : addNewTour
    Created on : 28-Feb-2013, 14:16:45
    Author     : Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
--%>

<%@page import="QuestionsAndAnswers.Question"%>
<%@page import="QuestionsAndAnswers.QuestionSet"%>
<%@page import="QuestionsAndAnswers.QuestionSetManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businessDomainObjects.Exhibit"%>
<%@page import="businessDomainObjects.ExhibitManager"%>
<%@page import="businessDomainObjects.TourManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a new tour to the database</title>
    </head>
    <body>
        <h1> Add a new tour to the database </h1> 
        <form action="/MuseumServer/addTour.do">
            <h2>Select which exhibits correspond to the tour:</h2>
            <%
                ExhibitManager em = (ExhibitManager) getServletContext().getAttribute("exhibitManager");
                ArrayList<Exhibit> exhibits = em.getListOfExhibits();
                if (exhibits.isEmpty()) {
                    out.println("<h3>No exhibits are currently recorded in the database</h3>");
                } else {
                    out.println("Name of tour: <input type='text' name='tourName' size='50' maxlength='50'/><br/>");
                    out.println("Description of tour: <input type='text' name='tourDescription' size='150' maxlength='200'/><br/>");
                    out.println("<br />Select exhibits for this tour:<br />");
                    for (Exhibit e : exhibits) {
                        String description = e.getDescription().length() > 100 ? e.getDescription().substring(0, 100) + "....." : e.getDescription();
                        String name = e.getName();
                        int ID = e.getExhibitID();
                        out.println("<input type=\"checkbox\" name=\"exhibitID\" value=\"" + ID + "\">" + name + "(<i>" + description + "</i>)<br>");
                    }
            %>
            
            
            Select a Question Set:<br />
            <select name="questionSet">
                <%
                    QuestionSetManager qsm = (QuestionSetManager) request.getServletContext().getAttribute("questionSetManager");

                    QuestionSet[] qs = qsm.getAllQuestionSets();

                    out.println("<option value=\"new\">Add New Question Set</option>");
                    for(QuestionSet currentQuestionSet : qs){
                        out.println("<option value=\"" + currentQuestionSet.getId() + "\">"
                                + currentQuestionSet.getName() + "</option>");
                    }

                %>
            </select><br /><br />
            
            Name of tour: <input type="text" name="tourName" size="50" maxlength="50"/><br/>"
            
            <br /><br />
            <%      
                Question[] allQuestions = qsm.getAllQuestions();
                for(Question currentQuestion : allQuestions){
                    // TODO: text and checkboxs output
                    
                }
                
                out.println("Name of tour: <input type='text' name='tourName' size='50' maxlength='50'/><br/>");
                out.println("Description of tour: <input type='text' name='tourDescription' size='150' maxlength='200'/><br/>");
                out.println("<br />Select exhibits for this tour:<br />");
                for (Exhibit e : exhibits) {
                    String description = e.getDescription().length() > 100 ? e.getDescription().substring(0, 100) + "....." : e.getDescription();
                    String name = e.getName();
                    int ID = e.getExhibitID();
                    out.println("<input type=\"checkbox\" name=\"exhibitID\" value=\"" + ID + "\">" + name + "(<i>" + description + "</i>)<br>");
                }
            
                    out.println("<input type=\"submit\" value=\"submit\"/>");
                } // end else
            %>
        </form>
        <%
            String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
        %>
    </body>
</html>
