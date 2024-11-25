package com.waggy.javacasestudy.service.iservice;

import java.util.List;

public interface IServiceProduct<P>{
    List<P> getAllProducts();
    P getProductById(int id);
    boolean addProduct(P p);
    void deleteProduct(int id);
    void updateProduct(int id ,P p);
}
