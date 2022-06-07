package com.example.ghost_storage.Storage;

import com.example.ghost_storage.Model.Company;
import com.example.ghost_storage.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CompanyRepo extends JpaRepository<Company, Long> {
    Company findCompanyById(Long companyId);

    Company findCompanyByName(String companyName);
}
