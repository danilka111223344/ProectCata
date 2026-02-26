package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Cemen", "Bakin", (byte) 18);
        userService.saveUser("Vladimir", "Korobov", (byte) 20);
        userService.saveUser("Alexandr", "Kosstilev", (byte) 13);
        userService.saveUser("Rodrigo", "Baldini", (byte) 30);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}