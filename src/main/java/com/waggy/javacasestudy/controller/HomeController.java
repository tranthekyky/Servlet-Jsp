package com.waggy.javacasestudy.controller;

import com.waggy.javacasestudy.model.Account;
import com.waggy.javacasestudy.model.Category;
import com.waggy.javacasestudy.model.Product;
import com.waggy.javacasestudy.service.cart.CartService;
import com.waggy.javacasestudy.service.category.CategoryService;
import com.waggy.javacasestudy.service.login.LoginService;
import com.waggy.javacasestudy.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "homeController" , value = "/waggyHome")
public class HomeController extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    LoginService loginService = new LoginService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            switch (action) {
                case "showHome":
                    showHomePage(req, resp);
                    break;
                case "showProducts":
                    showShopPage(req, resp);
                    break;
                case"showProductsByCategory":
                    showProductsByCategory(req, resp);
                    break;
                case "showProductsByPrice" :
                    showProductsByPrice(req, resp);
                    break;
                case "login" :
                    showFormLogin(req, resp);
                    break;
                case "register" :
                    showFormRegister(req, resp);
                    break;
                case "logout":
                    logout(req, resp);
                    break;
                case "manager" :
                    managerProduct(req, resp);
                    break;
                case "showProductInManager" :
                    showProductManager(req, resp);
                    break;
                case "showFormAddProduct" :
                    showFormAddProduct(req, resp);
                    break;
                case "deleteProduct" :
                    deleteProductById(req, resp);
                    break;
                case "showFormUpdateProduct" :
                    showFormUpdateProduct(req, resp);
                    break;


            }



    }
    public void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/home.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void showShopPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productService.getAllProducts() ;
        req.setAttribute("list", list);
        List<Category> listC = categoryService.getAllCategory() ;
        req.setAttribute("listC", listC);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/shop.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void showProductsByCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idC = Integer.parseInt(req.getParameter("idC"));
        List<Product> list = productService.getProductsByCategory(idC) ;
        req.setAttribute("list", list);
        List<Category> listC = categoryService.getAllCategory() ;
        req.setAttribute("listC", listC);
        Category cate = categoryService.getCategoryById(idC) ;
        req.setAttribute("cate", cate);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/shop.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void showProductsByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double minPrice = Double.parseDouble(req.getParameter("min"));
        double maxPrice = Double.parseDouble(req.getParameter("max"));
        List<Product> list = productService.getProductByPrice(minPrice, maxPrice) ;
        req.setAttribute("list", list);
        List<Category> listC = categoryService.getAllCategory() ;
        req.setAttribute("listC", listC);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/shop.jsp");
        requestDispatcher.forward(req, resp);

    }
    public void showFormLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/login.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void showFormRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/register.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("acc");
        resp.sendRedirect("/waggy/home.jsp");
    }
    public void managerProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/manager.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void showProductManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> listManager = productService.getAllProducts() ;
        req.setAttribute("listManager", listManager);
        List<Category> listC = categoryService.getAllCategory() ;
        req.setAttribute("listCManager", listC);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/manager.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void showFormAddProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/create.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void deleteProductById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idP"));
        productService.deleteProduct(id);
        req.setAttribute("notification", "Xoá sản phẩm có id = " + id + " thành công !");
        List<Product> listManager = productService.getAllProducts() ;
        req.setAttribute("listManager", listManager);
        List<Category> listC = categoryService.getAllCategory() ;
        req.setAttribute("listCManager", listC);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/manager.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void showFormUpdateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idP"));
        Product product = productService.getProductById(id);
        req.setAttribute("productU", product);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/update.jsp");
        requestDispatcher.forward(req, resp);
    }





    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "submitAccount" :
                submitAccount(req, resp);
             break;
            case "createAccount":
                createAccount(req, resp);
             break;
            case "searchProduct" :
                searchProductByName(req, resp);
                break;
            case "addProduct" :
                addProduct(req, resp);
                break;
            case "updateProduct" :
                updateProduct(req,resp);
                break;
        }
    }
    public void submitAccount (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("account");
        String password = req.getParameter("password");
        Account acc = loginService.getAccount(name, password);
        if (acc == null) {
            req.setAttribute("error" , "Tài khoản không hợp lệ !!");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/login.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("acc", acc);
            resp.sendRedirect("/waggy/home.jsp");
        }


    }
    public void createAccount (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("account");
        String password = req.getParameter("password");
        String re_password = req.getParameter("re-password");
        if (!password.equals(re_password)) {
            req.setAttribute("error" , "Mật khẩu không trùng !!");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/register.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            if (loginService.isCheckNameDuplicate(name)) {
                loginService.createAccount(name ,password);
                req.setAttribute("error", "Đăng ký thành công !!");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/register.jsp");
                requestDispatcher.forward(req, resp);
            }else {
                req.setAttribute("error", "Tài khoản này đã tồn tại !!");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/register.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }
    public void searchProductByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name-search");
        List<Product> list = productService.searchProductByName(name);
        req.setAttribute("list", list);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/shop.jsp");
        requestDispatcher.forward(req, resp);
    }
    public void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name-add");
        double price = Double.parseDouble(req.getParameter("price-add"));
        int quantity = Integer.parseInt(req.getParameter("quantity-add"));
        String image = req.getParameter("image-add");
        String description = req.getParameter("description-add");
        int categoryId = Integer.parseInt(req.getParameter("category-id"));
        Product product = new Product(name , price, quantity , image, description, categoryId);
        if (productService.addProduct(product)) {
            req.setAttribute("notification", "Thêm sản phẩm thành công !");
            List<Product> listManager = productService.getAllProducts() ;
            req.setAttribute("listManager", listManager);
            List<Category> listC = categoryService.getAllCategory() ;
            req.setAttribute("listCManager", listC);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/manager.jsp");
            requestDispatcher.forward(req, resp);
        }

    }
    public void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("idP"));
        String name = req.getParameter("name-update");
        double price = Double.parseDouble(req.getParameter("price-update"));
        int quantity = Integer.parseInt(req.getParameter("quantity-update"));
        String image = req.getParameter("image-update");
        String description = req.getParameter("description-update");
        int categoryId = Integer.parseInt(req.getParameter("category-id"));
        Product product = new Product( name, price, quantity , image, description, categoryId);
        productService.updateProduct(productId,product);
        req.setAttribute("notification", "Sửa sản phẩm có id = " + productId + " thành công !!");
        List<Product> listManager = productService.getAllProducts() ;
        req.setAttribute("listManager", listManager);
        List<Category> listC = categoryService.getAllCategory() ;
        req.setAttribute("listCManager", listC);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waggy/manager.jsp");
        requestDispatcher.forward(req, resp);



    }


}
