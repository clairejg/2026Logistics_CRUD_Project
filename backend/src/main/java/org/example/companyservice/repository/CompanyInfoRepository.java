package org.example.companyservice.repository;

import org.example.companyservice.model.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Integer> {}
