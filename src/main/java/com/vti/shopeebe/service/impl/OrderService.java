package com.vti.shopeebe.service.impl;

import com.vti.shopeebe.modal.dto.BaseRequest;
import com.vti.shopeebe.modal.entity.Account;
import com.vti.shopeebe.modal.entity.Order;
import com.vti.shopeebe.modal.entity.Product;
import com.vti.shopeebe.modal.entity.StatusOrder;
import com.vti.shopeebe.modal.request.CreateOrderRequest;
import com.vti.shopeebe.modal.request.SearchOrderRequest;
import com.vti.shopeebe.modal.request.UpdateOrderRequest;
import com.vti.shopeebe.repository.OrderRepository;
import com.vti.shopeebe.repository.specification.OrderSpecification;
import com.vti.shopeebe.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Order> search(SearchOrderRequest request) {
        Specification<Order> condition = OrderSpecification.buildCondition(request);
        PageRequest pageRequest = BaseRequest.buildPageRequest(request);
        return repository.findAll(condition, pageRequest);
    }


    @Override
    public Order getById(int id) {
        Optional<Order> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void create(CreateOrderRequest request) {
        Order order = new Order();
        BeanUtils.copyProperties(request, order);
        Account account = accountService.getById(request.getAccountId());
        Product product = productService.getById(request.getProductId());
        if (account != null && product != null) {
            order.setOrderBy(account);
            order.setProductId(product);
            order.setCreateDate(new Date());
            order.setStatus(StatusOrder.PENDING);
            repository.save(order);
        }
    }

    @Override
    public Order update(int id, UpdateOrderRequest request) {
        Order orderDb = getById(id);
        if (orderDb != null) {
            BeanUtils.copyProperties(request, orderDb);
            orderDb.setId(id);
            repository.save(orderDb);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void buyProduct(int id) {
        Order order = getById(id);
        order.setStatus(StatusOrder.DONE);
        repository.save(order);
    }

    @Override
    public void cancelProduct(int id) {
        Order order = getById(id);
        order.setStatus(StatusOrder.CANCEL);
        repository.save(order);
    }


}
