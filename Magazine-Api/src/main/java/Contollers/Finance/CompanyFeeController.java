/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contollers.Finance;

import DB.DAOs.Magazine.Financials.CompanyFeeDAO;
import BackendUtilities.Parser;
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

    /**
     * Manage the company fee actions, controller
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ArrayList<Double> list = new ArrayList<>();
        switch (request.getParameter("action")) {
            case "ALL":
                list = new CompanyFeeDAO().select();
                break;
        }
        response.getWriter().append(new Parser().toJSON(list, new ArrayList<Double>().getClass()));
    }

}
