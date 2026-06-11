package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {

    void save(Role role);

    Role findByName(String name);

    List<Role> findAll();

}
