package org.example.companyservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.companyservice.model.CompanyInfo;
import org.example.companyservice.service.CompanyInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CompanyInfoController {

    private final CompanyInfoService service;

    @GetMapping
    public List<CompanyInfo> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public CompanyInfo getById(@PathVariable Integer id) { return service.getById(id); }

    @PostMapping
    public CompanyInfo create(@RequestBody CompanyInfo c) { return service.create(c); }

    @PutMapping("/{id}")
    public CompanyInfo update(@PathVariable Integer id, @RequestBody CompanyInfo c) { return service.update(id, c); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
