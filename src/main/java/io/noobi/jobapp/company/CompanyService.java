package io.noobi.jobapp.company;


import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void createCompany(Company company);

    boolean deleteCompanyById(Long id);

    boolean updateCompany(Long id, Company updatedCompany);
}
