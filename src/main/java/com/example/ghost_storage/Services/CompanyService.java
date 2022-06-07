package com.example.ghost_storage.Services;

import com.example.ghost_storage.Model.Company;
import com.example.ghost_storage.Model.CompanyRole;
import com.example.ghost_storage.Model.Role;
import com.example.ghost_storage.Model.User;
import com.example.ghost_storage.Services.MailSender;
import com.example.ghost_storage.Storage.CompanyRepo;
import com.example.ghost_storage.Storage.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

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

    public Set<Company> getCompanies(){
        return companyRepo.findAllByIdNotNull();
    }

    public Company findCompanyByName(String name) { return companyRepo.findCompanyByName(name); }

    public boolean createCompany(String companyName, User user){
        Company companyFromDb = companyRepo.findCompanyByName(companyName);
        if (companyFromDb != null)
            return false;
        user.setCompany_roles(Collections.singleton(CompanyRole.ADMIN));
        Company company = new Company(companyName);
        company.addUser(user);
        companyRepo.save(company);
        return true;
    }
}
