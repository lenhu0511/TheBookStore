/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhultc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhultc.cart.Cart;
import nhultc.tbl_orderdetail.Tbl_OrderDetailDTO;

/**
 *
 * @author Dell
 */
public class AddToCartServlet extends HttpServlet {
    
    private final String INIT_SEARCH_PAGE_SERVLET = "InitSearchPageServlet";

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
        
        String url = INIT_SEARCH_PAGE_SERVLET;
        
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("USER") != null) {
                boolean isAdmin = (boolean) session.getAttribute("ADMIN");
                if (!isAdmin) {
                    // search by name value 
                    String searchByNameValue = request.getParameter("searchValue");

                    // search by range of money value
                    String minPriceValue = request.getParameter("txtMinPrice");
                    String maxPriceValue = request.getParameter("txtMaxPrice");

                    // search by category
                    String categoryValue = request.getParameter("category");

                    BigDecimal totalValue = BigDecimal.ZERO;

                    Cart cart = (Cart) session.getAttribute("CART");

                    if (cart == null) {
                        cart = new Cart();
                    }

                    // get detail
                    String title = request.getParameter("txtTitle");
                    String priceStr = request.getParameter("txtPrice");
                    BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceStr));
                    BigDecimal totalDetail = price;
                    Tbl_OrderDetailDTO oDto = new Tbl_OrderDetailDTO(1, price, totalDetail, title);
                    // id book
                    int id = Integer.parseInt(request.getParameter("txtId"));

                    cart.addItemToCart(id, oDto);

                    //set total
                    for (Map.Entry<Integer, Tbl_OrderDetailDTO> item : cart.getItems().entrySet()) {
                        totalValue = totalValue.add(item.getValue().getTotal()).stripTrailingZeros();
                    }

                    session.setAttribute("CART", cart);
                    session.removeAttribute("DISCOUNTCODE");
                    session.removeAttribute("DISCOUNT");

                    session.setAttribute("TOTAL", totalValue);

                    if (!searchByNameValue.isEmpty()) {
                        url = "ServletDispatcher?"
                                + "btAction=Search"
                                + "&txtSearch="
                                + searchByNameValue;
                    } else if (!minPriceValue.isEmpty() || !maxPriceValue.isEmpty()) {
                        url = "ServletDispatcher?"
                                + "btAction=Search"
                                + "&txtMinPrice="
                                + minPriceValue
                                + "&txtMaxPrice="
                                + maxPriceValue;
                    } else if (!categoryValue.isEmpty()) {
                        url = "ServletDispatcher?"
                                + "btAction=Search"
                                + "&category="
                                + categoryValue;
                    }
                }
            }
        } finally {
            response.sendRedirect(url);
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
