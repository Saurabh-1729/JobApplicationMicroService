package com.saurabhs.companyms.Company;

import java.util.List;


public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Long id, Company updatedCompany);
    void createCompany(Company company);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
}
