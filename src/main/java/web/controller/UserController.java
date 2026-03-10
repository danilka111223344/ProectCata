package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("requestusers", users);
        return "users";
    }

    @GetMapping("/new")
    public String newUSer(ModelMap model) {
        model.addAttribute("newuser", new User());
        return "users/new";
    }

    @GetMapping("/change")
    public String changeUser(ModelMap model, @RequestParam("id") Long id) {
        User user = userService.find(id);
        model.addAttribute("changeuser", user);
        return "users/change";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping("/change")
    public String changeUser(@RequestParam("id") Long id, @ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }
}
