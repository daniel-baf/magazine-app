/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contollers.Logs;

import APIErrors.SignupMessage;
import DB.Domain.Users.User;
import Models.UserModel;
import Parsers.Parser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "UserUpdater", urlPatterns = {"/UserUpdater"})
public class UserUpdater extends HttpServlet {

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
//        processRequest(request, response);
        Parser parser = new Parser();
        try {
            SignupMessage message = new UserModel().updateUser(request.getReader());
            response.getWriter().append(parser.getJsonFromObject(message, SignupMessage.class));
        } catch (Exception e) {
            response.getWriter().append(parser.getJsonFromObject(new SignupMessage("Error al intentar Iniciar sesion en [UserUpdater] " + e.getMessage(), null), SignupMessage.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
