/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhultc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
public class ServletDispatcher extends HttpServlet {
    
    private final String INIT_SEARCH_PAGE_SERVLET = "InitSearchPageServlet";
    private final String SEARCH_SERVLET = "SearchServlet";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String CREATE_BOOK_SERVLET = "CreateBookServlet";
    private final String UPDATE_BOOK_SERVLET = "UpdateBookServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String ADD_TO_CART_SERVLET = "AddToCartServlet";
    private final String UPDATE_ITEM_SERVLET = "UpdateItemServlet";
    private final String CONFIRM_SERVLET = "ConfirmServlet";
    private final String REMOVE_ITEM_SERVLET = "RemoveItemServlet";
    private final String INIT_DISCOUNT_SERVLET = "InitDiscountServlet";
    private final String CREATE_DISCOUNT_SERVLET = "CreateDiscountServlet";
    private final String SUBMIT_DISCOUNT_SERVLET = "SubmitDiscountServlet";
    private final String INIT_HISTORY_SERVLET = "InitHistoryServlet";
    private final String SEARCH_HISTORY_SERVLET = "SearchHistoryServlet";
    
    
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
        
        PrintWriter out = response.getWriter();
        
        String button = request.getParameter("btAction");
        String url = INIT_SEARCH_PAGE_SERVLET;
        
        try {
            if(button ==  null){
              url = INIT_SEARCH_PAGE_SERVLET;
            } else if(button.equals("Search")){
                url = SEARCH_SERVLET;
            } else if(button.equals("Login")){
                url = LOGIN_SERVLET;
            } else if(button.equals("Logout")){
                url = LOGOUT_SERVLET;
            } else if(button.equals("Create")){
                url = CREATE_BOOK_SERVLET;
            } else if(button.equals("Update")){
                url = UPDATE_BOOK_SERVLET;
            } else if(button.equals("Delete")){
                url = DELETE_SERVLET;
            } else if(button.equals("Add to Cart")){
                url = ADD_TO_CART_SERVLET;
            } else if(button.equals("Update Item")){
                url = UPDATE_ITEM_SERVLET;
            } else if(button.equals("Confirm")){
                url = CONFIRM_SERVLET;
            } else if(button.equals("RemoveItem")){
                url = REMOVE_ITEM_SERVLET;
            } else if(button.equals("Create new discount")){
                url = INIT_DISCOUNT_SERVLET;
            } else if(button.equals("Create discount code")){
                url = CREATE_DISCOUNT_SERVLET;
            } else if(button.equals("Use code")){
                url = SUBMIT_DISCOUNT_SERVLET;
            } else if(button.equals("History")){
                url = INIT_HISTORY_SERVLET;
            } else if(button.equals("Search history")){
                url = SEARCH_HISTORY_SERVLET;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
