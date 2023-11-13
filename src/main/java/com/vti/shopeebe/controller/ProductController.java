package com.vti.shopeebe.controller;


import com.vti.shopeebe.modal.entity.Product;
import com.vti.shopeebe.modal.request.CreateProductRequest;
import com.vti.shopeebe.modal.request.SearchProductRequest;
import com.vti.shopeebe.modal.request.UpdateProductRequest;
import com.vti.shopeebe.service.impl.ProductService;
import com.vti.shopeebe.validate.ProductIdExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@CrossOrigin("*")
@Validated
public class ProductController {

    @Autowired
    public ProductService service;

    @GetMapping("/get-all")
    public List<Product> getAll(){
        return service.getAll();
    }

    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchProductRequest request) {
        return service.search(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Product getById(@PathVariable @ProductIdExists int id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody CreateProductRequest request){
        service.create(request);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody UpdateProductRequest request, @PathVariable int id){
        service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}
