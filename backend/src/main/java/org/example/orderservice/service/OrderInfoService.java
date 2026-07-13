package org.example.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.model.OrderInfo;
import org.example.orderservice.repository.OrderInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderInfoService {

    private final OrderInfoRepository repo;

    public List<OrderInfo> getAll(){ return repo.findAll();}
    // findAll() is "JPA built-in method" or "JpaRepository method"
    public OrderInfo getById(Integer id){
        return repo.findById(id).orElseThrow(()-> new RuntimeException("OrderInfo not found: "+ id));
    }

    public OrderInfo create(OrderInfo o){
        return repo.save(o);
    }

    public OrderInfo update(Integer id, OrderInfo o){
        o.setOrderId(id);
        return repo.save(o);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
}