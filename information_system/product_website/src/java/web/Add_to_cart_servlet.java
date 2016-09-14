package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.DatabaseProductManager;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author milba845
 */
@WebServlet(urlPatterns = {"/Add_to_cart_servlet"})
public class Add_to_cart_servlet extends HttpServlet {

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
        

        DatabaseProductManager dao = new DatabaseProductManager();
        HttpSession session = request.getSession();
        String quantity = request.getParameter("quantity");
        Product product = (Product)session.getAttribute("product");
        if(quantity.isEmpty()){
            response.sendRedirect("quantity_wanted.jsp");
        }else{
            if(product.getQuantityInStock() >= Integer.parseInt(quantity)){
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantityPurchased(Integer.parseInt(quantity));
                orderItem.setProduct(product);
                Order order = (Order)session.getAttribute("order");
                orderItem.setOrder(order);
                order.addItem(orderItem);
                session.setAttribute("order", order);
                session.removeAttribute("product");
                response.sendRedirect("checkout.jsp");
            }else{
                response.sendError(422, "there is not enough quantity in stock");
            }
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
