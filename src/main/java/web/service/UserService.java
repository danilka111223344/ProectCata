package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void update(User user);

    User find(Long id);

    void delete(Long id);

    List<User> listUsers();
}
