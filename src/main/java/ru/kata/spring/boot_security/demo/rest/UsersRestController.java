package ru.kata.spring.boot_security.demo.rest;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UsersRestController {

    private UserService userService;
    private RoleService roleService;

    public UsersRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> showUsers() {
        return userService.listUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.find(id);
    }

    @PostMapping("/users/add")
    public User addUser(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            for (Role role : user.getRoles()) {
                Role existRole = roleService.findByName(role.getName());
                if (existRole != null) {
                    roles.add(existRole);
                }
            }
        } else {
            Role defaultrole = roleService.findByName("ROLE_USER");
            if (defaultrole != null) {
                roles.add(defaultrole);
            }
        }
        user.setRoles(roles);
        userService.add(user);
        return user;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User authUser = userService.find(id);
        authUser.setName(user.getName());
        authUser.setLastName(user.getLastName());
        authUser.setAge(user.getAge());
        authUser.setUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            authUser.setPassword(user.getPassword());
        }
        Set<Role> roles = new HashSet<>();
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            for (Role role : user.getRoles()) {
                Role existRole = roleService.findByName(role.getName());
                if (existRole != null) {
                    roles.add(existRole);
                }
            }
            authUser.setRoles(roles);
        }

        userService.update(authUser);
        return authUser;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }
}
