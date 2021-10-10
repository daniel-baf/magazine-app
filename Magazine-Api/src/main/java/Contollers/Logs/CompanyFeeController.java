/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contollers.Logs;

import DB.DAOs.Magazine.CompanyFeeDAO;
import Parsers.Parser;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "CompanyFeeController", urlPatterns = {"/CompanyFeeController"})
public class CompanyFeeController extends HttpServlet {

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
        ArrayList<Double> list = new ArrayList<>();
        switch (request.getParameter("action")) {
            case "ALL":
                list = new CompanyFeeDAO().select();
                break;
        }
        response.getWriter().append(new Parser().getJsonFromObject(list, new ArrayList<Double>().getClass()));
    }

}
