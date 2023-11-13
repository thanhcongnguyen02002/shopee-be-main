package com.vti.shopeebe.controller;

import com.vti.shopeebe.modal.entity.Order;
import com.vti.shopeebe.modal.request.CreateOrderRequest;
import com.vti.shopeebe.modal.request.SearchOrderRequest;
import com.vti.shopeebe.modal.request.UpdateOrderRequest;
import com.vti.shopeebe.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin("*")
@Validated
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/get-all")
    public List<Order> getAll(){
        return service.getAll();
    }

    @PostMapping("/search")
    public Page<Order> search(@RequestBody SearchOrderRequest request) {
        return service.search(request);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody @Valid CreateOrderRequest request) {
        service.create(request);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody UpdateOrderRequest request, @PathVariable int id){
        service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PostMapping("/buy-product/{id}")
    public void buyProduct(@PathVariable int id) {
        service.buyProduct(id);
    }

    @PostMapping("/cancel-product/{id}")
    public void cancelProduct(@PathVariable int id) {
        service.cancelProduct(id);
    }
}
