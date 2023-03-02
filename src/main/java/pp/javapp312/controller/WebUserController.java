package pp.javapp312.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "/all-users";
    }

    @PostMapping(value = "/addNewUser")
    public String addNewUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/showUserById")
    public String showUserById(@RequestParam("id") int id,
                             Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user-info";
    }
    @PatchMapping("/saveUser")
    public String updateUser(@ModelAttribute("changedUser") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @DeleteMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
