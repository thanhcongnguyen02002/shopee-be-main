package com.vti.shopeebe.validate;

import com.vti.shopeebe.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// Ghi chú: ConstraintValidator<Annoitation đã tạo, kiểu dữ liệu muốn kiếm tra>
public class ProductIdExistsValidator implements ConstraintValidator<ProductIdExists, Integer> {

    @Autowired
    ProductRepository repository;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        // Logic kiểm tra dữ liệu
        return repository.existsById(integer);
    }
}
