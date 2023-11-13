package com.vti.shopeebe.service;

import com.vti.shopeebe.modal.entity.Product;
import com.vti.shopeebe.modal.request.CreateProductRequest;
import com.vti.shopeebe.modal.request.SearchProductRequest;
import com.vti.shopeebe.modal.request.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    List<Product> getAll();

    Page<Product> search(SearchProductRequest request);

    Product getById(int id);

    void create(CreateProductRequest request);

    Product update(int id, UpdateProductRequest request);

    void delete(int id);
}
