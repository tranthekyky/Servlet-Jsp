package com.waggy.javacasestudy.controller;


import com.waggy.javacasestudy.model.Account;
import com.waggy.javacasestudy.model.Cart;
import com.waggy.javacasestudy.model.CartDetail;
import com.waggy.javacasestudy.model.Product;
import com.waggy.javacasestudy.service.cart.CartService;
import com.waggy.javacasestudy.service.category.CategoryService;
import com.waggy.javacasestudy.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "cartController" ,value = "/cart")
public class CartController extends HttpServlet {
    CartService cartService = new CartService();
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    HomeController homeController = new HomeController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "showCart" :
                showCart(req, resp);
                break;
            case "addToCart" :
                addToCartt(req ,resp) ;
                break;
            case "deleteToCart" :
                deleteProductInCart(req, resp);
                break;

        }
    }
    public void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idC = Integer.parseInt(req.getParameter("idC"));
        int idCart = cartService.getIdCartByUserId(idC);
        showCartDetailById(req , resp , idCart);

    }
    public void addToCartt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idP  = Integer.parseInt(req.getParameter("idP"));
        HttpSession session = req.getSession(false);
        Account account = (Account) session.getAttribute("acc");
        int idUser = account.getIdAcc();
        int idCart = cartService.getIdCartByUserId(idUser);
        if (idCart == 0) {
            cartService.createCart(idUser);
            idCart = cartService.getIdCartByUserId(idUser);
            cartService.addProductInCart(idCart, idP);
        }else {
            if (cartService.isCheckProductInCart(idCart, idP)) {
                cartService.updateQuantity(idCart, idP);
            }else {
                cartService.addProductInCart(idCart, idP);
            }
        }
        showCartDetailById(req , resp , idCart);
    }
    public void showCartDetailById(HttpServletRequest req, HttpServletResponse resp , int idCart) throws ServletException, IOException {
        List<CartDetail> list = cartService.getAllProductInCart(idCart) ;
        List<Product> products = new ArrayList<>();
        int total_quantity = 0;
        double total_price = 0;
        for (CartDetail cartDetail : list) {
            Product pro = productService.getProductById(cartDetail.getIdProduct()) ;
            pro.setQuantity(cartDetail.getQuantity());
            products.add(pro);
            total_quantity += pro.getQuantity();
            total_price += (pro.getPrice() * pro.getQuantity());
        }
        req.setAttribute("total_quantity", total_quantity);
        req.setAttribute("total_price", total_price);
        req.setAttribute("productsInCart", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/waggy/cart.jsp");
        dispatcher.forward(req, resp);
    }
    public void deleteProductInCart (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idP  = Integer.parseInt(req.getParameter("idP"));
        HttpSession session = req.getSession(false);
        Account account = (Account) session.getAttribute("acc");
        int idUser = account.getIdAcc();
        int idCart = cartService.getIdCartByUserId(idUser);
        cartService.deleteProductInCart(idCart, idP);
        req.setAttribute("textD", true);
        showCartDetailById(req , resp , idCart);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "updateCart" :
                updateCart(req , resp);
                break;
            case "payCart" :
                payCart(req , resp);
                break;
        }

    }
    public void updateCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantityUpdate  = Integer.parseInt(req.getParameter("quantityInCart"));
        int idP  = Integer.parseInt(req.getParameter("idP"));
        HttpSession session = req.getSession(false);
        Account account = (Account) session.getAttribute("acc");
        int idUser = account.getIdAcc();
        int idCart = cartService.getIdCartByUserId(idUser);
        cartService.updateQuantityInCart(idCart, idP, quantityUpdate);
        req.setAttribute("text" , true);
        showCartDetailById(req , resp , idCart);
    }
    public void payCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idA =  Integer.parseInt(req.getParameter("idA"));
        int idCart = cartService.getIdCartByUserId(idA);
        if (cartService.isCheckCartNotNull(idCart)) {
            cartService.payProductInCart(idCart);
            req.setAttribute("textP" , true);
        }else {
            req.setAttribute("textNull" , true);
        }
        showCartDetailById(req , resp , idCart);

    }


}
