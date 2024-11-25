package com.waggy.javacasestudy.controller;

import com.waggy.javacasestudy.model.Category;
import com.waggy.javacasestudy.model.Product;
import com.waggy.javacasestudy.service.category.CategoryService;
import com.waggy.javacasestudy.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name ="searchManagerController" , value = "/searchInManager")
public class SearchManagerController extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String id = request.getParameter("id");
       String cate = request.getParameter("cate");
       String min = request.getParameter("min");
       String max = request.getParameter("max");
       String name = request.getParameter("name");
        if (id != null && !id.isEmpty() ) {
            int idS = Integer.parseInt(id);
            Product pro = productService.getProductById(idS);
            request.setAttribute("item", pro);
            List<Category> listC = categoryService.getAllCategory() ;
            request.setAttribute("listCManager", listC);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/waggy/manager.jsp");
            dispatcher.forward(request, response);
        }else {
            if (cate != null && !cate.isEmpty() ) {
                int cateS = Integer.parseInt(cate);
                List<Product> pro = productService.getProductsByCategory(cateS);
                request.setAttribute("listManager", pro);
                List<Category> listC = categoryService.getAllCategory() ;
                request.setAttribute("listCManager", listC);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/waggy/manager.jsp");
                dispatcher.forward(request, response);
            }else {
                if (min != null && max != null && !min.isEmpty()  && !max.isEmpty()) {
                    double minS = Double.parseDouble(min);
                    double maxS = Double.parseDouble(max);
                    List<Product> pro = productService.getProductByPrice(minS, maxS);
                    request.setAttribute("listManager", pro);
                    List<Category> listC = categoryService.getAllCategory() ;
                    request.setAttribute("listCManager", listC);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/waggy/manager.jsp");
                    dispatcher.forward(request, response);
                }else {
                    if (name != null && !name.isEmpty()) {
                        List<Product> pro = productService.searchProductByName(name) ;
                        request.setAttribute("listManager", pro);
                        List<Category> listC = categoryService.getAllCategory() ;
                        request.setAttribute("listCManager", listC);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/waggy/manager.jsp");
                        dispatcher.forward(request, response);
                    }else {
                        response.sendRedirect("/waggy/manager.jsp");
                    }
                }
            }
        }
    }
}
