package org.example.orderservice.model;
//telling Java where this file lives in your project,
//and signals that this file is a data/entity class for the company service
//It's where you put your data/entity classes that represent your database tables

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
//JPA is what lets you map Java classes to database tables
//The * means it imports all annotations from that package, like:
//@Entity → marks a class as a database table
//@Id → marks a field as the primary key
//@Column → maps a field to a table column
//@GeneratedValue → auto-generates ID values

@Data
//@Data is a Lombok annotation that automatically generates code at compile time — specifically getters, setters,
@Entity
@Table(name = "orderinfo", schema = "\"import\"")
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "order_date")
    private LocalDate orderDate;

}