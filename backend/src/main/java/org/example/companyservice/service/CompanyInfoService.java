package org.example.companyservice.service;

import lombok.RequiredArgsConstructor;
import org.example.companyservice.model.CompanyInfo;
import org.example.companyservice.repository.CompanyInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {

    private final CompanyInfoRepository repo;

    public List<CompanyInfo> getAll() {
        System.out.println("getAll() from service");
        return repo.findAll(); }

    public CompanyInfo getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("CompanyInfo not found: " + id));
    }

    public CompanyInfo create(CompanyInfo c) { return repo.save(c); }

    public CompanyInfo update(Integer id, CompanyInfo c) {
        c.setAccountId(id);
        return repo.save(c);
    }

    public void delete(Integer id) { repo.deleteById(id); }
}
