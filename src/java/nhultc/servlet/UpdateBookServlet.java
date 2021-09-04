/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhultc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhultc.tbl_book.Tbl_BookDAO;
import nhultc.tbl_category.Tbl_CategoryDAO;

/**
 *
 * @author Dell
 */
public class UpdateBookServlet extends HttpServlet {
    
    private final String INIT_SEARCH_PAGE_SERVLET = "InitSearchPageServlet";
    private final String UPDATE_BOOK_PAGE = "updateBook.jsp";
    
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
                if (isAdmin) {
                    url = UPDATE_BOOK_PAGE;
        
                    String title = request.getParameter("txtTitle");
                    String description = request.getParameter("txtDescription");
                    String price = request.getParameter("txtPrice");
                    String author = request.getParameter("txtAuthor");
                    String categoryName = request.getParameter("category");
                    String quantity = request.getParameter("txtQuantity");
                    String photo = request.getParameter("photo");
                    String importDate = request.getParameter("txtImportDate");
                    String id = request.getParameter("txtId");
            
                    Tbl_CategoryDAO cDao = new Tbl_CategoryDAO();
                    int categoryId = cDao.getCategoryId(categoryName);

                    Tbl_BookDAO bDao = new Tbl_BookDAO();
                    boolean result = bDao.updateBook(id, title, price, author, categoryId, photo, quantity, description, importDate);

                    if (result) {
                        url = INIT_SEARCH_PAGE_SERVLET;
                    }
                }
            }
        } catch (NamingException ex) {
            log("UpdateBookServlet_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateBookServlet_SQLException " + ex.getMessage());
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
