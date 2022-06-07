package com.example.ghost_storage.Controllers;

import com.example.ghost_storage.Model.Company;
import com.example.ghost_storage.Model.User;
import com.example.ghost_storage.Services.CompanyService;
import com.example.ghost_storage.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;
    private final CompanyService companyService;

    public RegistrationController(UserService userService, CompanyService companyService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping
    public String registration(Map<String, Object> model) {
        model.put("companies", companyService.getCompanies());
        return "registration";
    }

    @PostMapping("user")
    public String addUser(User user, @RequestParam String companyName, Map<String, Object> model) {
        Company company = companyService.findCompanyByName(companyName);

        if (!userService.addUser(user, company)) {
            model.put("message", "User exists!");
            return "registration";
        }

        return "check";
    }

    @PostMapping("company")
    public String addCompany(User user, @RequestParam String createdCompanyName, Map<String, Object> model) {
        if (!companyService.addCompany(createdCompanyName, user)){
            model.put("message", "Company exists!");
            return "registration";
        }
        Company company = companyService.findCompanyByName(createdCompanyName);
        if (!userService.addUser(user, company)) {
            model.put("message", "User exists!");
            return "registration";
        }

        return "check";
    }
}
