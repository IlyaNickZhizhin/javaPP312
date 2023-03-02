package pp.javapp312.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pp.javapp312.model.User;
import pp.javapp312.service.UserService;

import java.util.List;

@Controller
public class WebUserController {

    private final UserService userService;

    @Autowired
    public WebUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/start")
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "/all-users";
    }

    @PostMapping(value = "/addNewUser")
    public String addNewUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/start";
    }

    @PatchMapping("/updateUser")
    public String updateUser(@RequestParam("id") int id,
                             Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user-info";
    }
    @PutMapping("/saveUser")
    public String updateUser(@ModelAttribute("changedUser") User user) {
        userService.saveUser(user);
        return "redirect:/start";
    }

    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/start";
    }

}
