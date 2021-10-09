/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contollers.Logs;

import APIErrors.SignupMessage;
import ENUMS.DAOResults;
import Models.UserModel;
import Parsers.Parser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "UserActions", urlPatterns = {"/UserActions"})
public class UserActions extends HttpServlet {

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
        System.out.println("HOLAAAAAAAAAAAA");
        Parser parser = new Parser();
        try {
            SignupMessage signupMessage = (SignupMessage) parser.getObjectFromJson(parser.getBody(request.getReader()), SignupMessage.class);

            switch (signupMessage.getMessage()) {
                case "UPDATE_USER":
                    signupMessage = new UserModel().updateUser(request.getReader());
                    break;
                default:
                    signupMessage.setMessage(DAOResults.ERROR_ON_INSERT.getMessage());
            }
            response.getWriter().append(parser.getJsonFromObject(signupMessage, signupMessage.getClass()));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
//            response.getWriter().append(parser.getJsonFromObject(new SignupMessage("Error al intentar buscar categorias en [CategoriesSelectController]" + e.getMessage(), null), SignupMessage.class));
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
