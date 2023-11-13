package com.vti.shopeebe.service.impl;

import com.vti.shopeebe.exception.AppException;
import com.vti.shopeebe.exception.ErrorResponseBase;
import com.vti.shopeebe.modal.dto.BaseRequest;
import com.vti.shopeebe.modal.entity.Product;
import com.vti.shopeebe.modal.request.CreateProductRequest;
import com.vti.shopeebe.modal.request.SearchProductRequest;
import com.vti.shopeebe.modal.request.UpdateProductRequest;
import com.vti.shopeebe.repository.ProductRepository;
import com.vti.shopeebe.repository.specification.ProductSpecification;
import com.vti.shopeebe.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Product> search(SearchProductRequest request) {
        long minPrice = request.getMinPrice();
        long maxPrice = request.getMaxPrice();
        if (minPrice >= maxPrice) {
            throw new AppException(ErrorResponseBase.MIN_MAXINVALID);
        }

        Specification<Product> condition = ProductSpecification.buildCondition(request);
        PageRequest pageRequest = BaseRequest.buildPageRequest(request);

        return repository.findAll(condition, pageRequest);
    }

    @Override
    public Product getById(int id) {
        Optional<Product> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void create(CreateProductRequest request) {
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        repository.save(product);
    }

    @Override
    public Product update(int id, UpdateProductRequest request) {
        Product productDb = getById(id);
        if (productDb != null) {
            BeanUtils.copyProperties(request, productDb);
            productDb.setId(id);
            repository.save(productDb);
        }
        return null;
    }

    @Override
    public void  delete(int id) {
        repository.deleteById(id);
    }
}
