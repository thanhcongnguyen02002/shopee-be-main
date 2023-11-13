package com.vti.shopeebe.service;

import com.vti.shopeebe.modal.entity.Order;
import com.vti.shopeebe.modal.request.CreateOrderRequest;
import com.vti.shopeebe.modal.request.SearchOrderRequest;
import com.vti.shopeebe.modal.request.UpdateOrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {

    List<Order> getAll();

    Page<Order> search(SearchOrderRequest request);

    Order getById(int id);

    void create(CreateOrderRequest request);

    Order update(int id, UpdateOrderRequest request);

    void delete(int id);

    void buyProduct(int id);

    void cancelProduct(int id);
}
