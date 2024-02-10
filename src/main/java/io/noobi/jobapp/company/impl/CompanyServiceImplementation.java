package io.noobi.jobapp.company.impl;

import io.noobi.jobapp.company.Company;
import io.noobi.jobapp.company.CompanyRepository;
import io.noobi.jobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImplementation implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()) {
            Company company= companyOptional.get();
            if(updatedCompany.getName() != null)
                company.setName(updatedCompany.getName());
            if(updatedCompany.getDescription() != null)
                company.setDescription(updatedCompany.getDescription());
            if(updatedCompany.getJobs() != null)
                company.setJobs(updatedCompany.getJobs());

            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
