package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(PasswordEncoder passwordEncoder,
                           RoleService roleService,
                           UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("requestusers", users);
        return "admin";
    }

    @GetMapping("/new")
    public String newUSer(ModelMap model) {
        model.addAttribute("newuser", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "admin/new";
    }

    @GetMapping("/change")
    public String changeUser(ModelMap model, @RequestParam("id") Long id) {
        User user = userService.find(id);
        model.addAttribute("changeuser", user);
        model.addAttribute("allRoles", roleService.findAll());
        return "admin/change";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "rolename", required = false) List<String> names,
                             ModelMap model) {
        Set<Role> roles = new HashSet<>();
        if (names != null) {
            for (String name : names) {
                Role role = roleService.findByName(name);
                if (role != null) {
                    roles.add(role);
                }
            }
        } else {
            Role defaultrole = roleService.findByName("ROLE_USER");
            if (defaultrole != null) {
                roles.add(defaultrole);
            }
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/change")
    public String changeUser(@RequestParam("id") Long id,
                             @RequestParam(value = "rolename", required = false) List<String> names,
                             @ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
        if (names != null) {
            for (String name : names) {
                Role role = roleService.findByName(name);
                if (role != null) {
                    roles.add(role);
                }
            }
        } else {
            Role defaultrole = roleService.findByName("ROLE_USER");
            if (defaultrole != null) {
                roles.add(defaultrole);
            }
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(user);
        return "redirect:/admin";
    }
}
