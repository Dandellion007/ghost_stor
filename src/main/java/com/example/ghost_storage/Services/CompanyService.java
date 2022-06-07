package com.example.ghost_storage.Services;

import com.example.ghost_storage.Model.Company;
import com.example.ghost_storage.Model.User;
import com.example.ghost_storage.Services.MailSender;
import com.example.ghost_storage.Storage.CompanyRepo;
import com.example.ghost_storage.Storage.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final UserRepo userRepo;
    private final MailSender mailSender;
    private final CompanyRepo companyRepo;

    public CompanyService(UserRepo userRepo, MailSender mailSender, CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    public boolean addCompany(String companyName, User user){
        Company companyFromDb = companyRepo.findCompanyByName(companyName);
        if (companyFromDb != null)
            return false;
        Company company = new Company(companyName);
        company.addUser(user);
        user.setCompany(company);
        companyRepo.save(company);
        return true;
    }
}
