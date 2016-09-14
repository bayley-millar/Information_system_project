/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.StoreCustomer;
import domain.Customer;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 *
 * @author milba845
 */
@WebServlet(name = "Create_acc_servlet", urlPatterns = {"/Create_acc_servlet"})
public class Create_acc_servlet extends HttpServlet {

    StoreCustomer cust = new StoreCustomer();

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
        // try (PrintWriter out = response.getWriter()) {

        String username = request.getParameter("userName");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String creditcard = request.getParameter("creditCard");
        String password = request.getParameter("password");
        Customer new_acc = new Customer(username, name, email, address, creditcard, password);
        StoreCustomer dao = new StoreCustomer();

        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(cust);
        if (violations.isEmpty()) {
            cust.saveCustomer(new_acc);
            response.sendRedirect("/shop/home.jsp");

        } else {

            String validationMessage = "422 / UNPROCESSABLE ENTITY (WEBDAV) (RFC 4918)";
            response.sendError(422, validationMessage);

        }
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
