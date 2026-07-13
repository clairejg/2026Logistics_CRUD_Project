package org.example.orderservice.repository;

import org.example.orderservice.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {}

// Creates a repository interface that handles all database operations for CompanyInfo
//By extending JpaRepository, you get built-in methods for free:
//findAll() → get all records
//findById() → get one record by ID
//save() → insert or update
//delete() → delete a record

//JpaRepository takes 2 type arguments:
// JpaRepository<EntityClass, IDType>
// IDType → the data type of the primary key (@Id) in OrderInfo
//Integer