package com.example.ghost_storage.Controllers;

import com.example.ghost_storage.Model.Company;
import com.example.ghost_storage.Model.User;
import com.example.ghost_storage.Services.CompanyService;
import com.example.ghost_storage.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Console;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final CompanyService companyService;

    public RegistrationController(UserService userService, CompanyService companyService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Map<String, Object> model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, @RequestParam String companyName, Map<String, Object> model) {
        if (companyName != null) {
            if (!companyService.addCompany(companyName, user)){
                model.put("message", "Company exists!");
                return "registration";
            }
        }
        if (!userService.addUser(user)) {
            model.put("message", "User exists!");
            return "registration";
        }

        return "check";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated)
            model.addAttribute("message", "User successfully activated!");
        else
            model.addAttribute("message", "Activation code is not found!");

        return "login";
    }
}
