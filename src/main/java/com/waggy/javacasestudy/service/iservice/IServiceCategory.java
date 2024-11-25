package com.waggy.javacasestudy.service.iservice;

import java.util.List;

public interface IServiceCategory <C>{
    List<C> getAllCategory();
    C getCategoryById(int id);

}
