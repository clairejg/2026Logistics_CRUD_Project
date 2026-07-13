package org.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.model.OrderInfo;
import org.example.orderservice.service.OrderInfoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
//sets the base URL path for all endpoints in that controller.
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderInfoController {

    private final OrderInfoService service;

    @GetMapping
    public List<OrderInfo> getAll() { return service.getAll(); }
    //@GetMapping("/{id}")	== GET /api/orders/5

    @GetMapping("/{id}")
    public OrderInfo getById(@PathVariable Integer id) {
        return service.getById(id); }

    @PostMapping
    public OrderInfo create(@RequestBody OrderInfo o) { return service.create(o); }

    @PutMapping("/{id}")
    public OrderInfo update(@PathVariable Integer id, @RequestBody OrderInfo o) { return service.update(id, o); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}