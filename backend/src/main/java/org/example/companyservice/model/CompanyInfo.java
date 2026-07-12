package org.example.companyservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "\"CompanyInfo\"", schema = "\"import\"")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"AccountID\"")
    private Integer accountId;

    @Column(name = "\"CompanyName\"")
    private String companyName;

    @Column(name = "\"Address\"")
    private String address;

    @Column(name = "\"Country\"")
    private String country;

    @Column(name = "\"Phone\"")
    private String phone;
}
