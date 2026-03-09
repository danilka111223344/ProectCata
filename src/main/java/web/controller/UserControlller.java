package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.Model.User;
import web.Service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserControlller {
    private UserService userService;

    @Autowired
    public UserControlller(UserService userService) {
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
    public String ChangeUser(ModelMap model,@RequestParam("id") Long id) {
        User user = new User();
        user.setId(id);
        model.addAttribute("changeuser", user);
        return "users/change";
    }

    @PostMapping("/new")
    public String create(@RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("email") String email,
                         @RequestParam("age") int age, @RequestParam("phonenumber") String phonenumber) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAge(age);
        user.setPhonenumber(phonenumber);
        userService.add(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
        return "redirect:/users";
    }

    @PostMapping("/change")
    public String change(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("email") String email,
                         @RequestParam("age") int age, @RequestParam("phonenumber") String phonenumber) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAge(age);
        user.setPhonenumber(phonenumber);
        userService.update(user);
        return "redirect:/users";
    }


}
