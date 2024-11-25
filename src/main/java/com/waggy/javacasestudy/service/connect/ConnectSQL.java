package com.waggy.javacasestudy.service.connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectSQL {
    private static String URL = "jdbc:mysql://localhost:3306/waggy";
    private static String USER = "root";
    private static String PASS = "trantheky0411";
    public static Connection getConnectData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL , USER , PASS) ;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
