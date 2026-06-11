package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    User findByUsername(String username);

    User findById(Long id);

    void deleteById(Long id);

    List<User> listUsers();


}
