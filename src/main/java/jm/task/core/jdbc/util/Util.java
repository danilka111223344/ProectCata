package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/project1";
    private static final String username = "root";
    private static final  String password = "root";

    public Connection getCon() {
        Connection connection = null;
        try  {
            connection = DriverManager.getConnection(url, username, password );
            System.out.println("Соединение успешно");
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return connection ;

    }
    // реализуйте настройку соеденения с БД
}