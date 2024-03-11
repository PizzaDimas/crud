package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.modelDao.UserEntity;
import web.userService.UserService;


@Controller
@RequestMapping("/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String readById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/user";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("user") UserEntity user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") UserEntity user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("user") UserEntity user) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeById(id);
        return "redirect:/users";
    }
}
