package com.saurabhs.companyms.Company.impl;

import com.saurabhs.companyms.Company.Company;
import com.saurabhs.companyms.Company.CompanyRepository;
import com.saurabhs.companyms.Company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
//      Optional<Job> is used to safely handle the case where a Job might not exist in the database, and to avoid null pointer errors in a clean, readable way.
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        companyRepository.deleteById(id);
        return true;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
