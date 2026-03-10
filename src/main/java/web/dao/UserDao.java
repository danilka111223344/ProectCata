package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    User findById(Long id);

    void deleteById(Long id);

    List<User> listUsers();


}
