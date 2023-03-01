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

    //метод вызывающий шаблон index из resources/templates

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/start")
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "/all-users";
    }

    @RequestMapping(value = "/addNewUser")
    public String addNewUser(@RequestParam(name = "name") String name,
                             @RequestParam(name = "surname") String surname,
                             @RequestParam(name = "email") String email) {
        User user = new User(name, surname, email);
        userService.saveUser(user);
        return "redirect:/start";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("id") int id,
                             Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "/user-info";
    }
    @RequestMapping("/saveUser")
    public String updateUser(@RequestParam("id") int id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "surname") String surname,
                             @RequestParam(name = "email") String email) {
        User user = userService.getUser(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        userService.saveUser(user);
        return "redirect:/start";
    }

    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/start";
    }

}
