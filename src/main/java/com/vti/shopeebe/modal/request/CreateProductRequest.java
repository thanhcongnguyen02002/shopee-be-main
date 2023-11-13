package com.vti.shopeebe.modal.request;

import com.vti.shopeebe.modal.entity.ProductStatus;
import com.vti.shopeebe.modal.entity.ProductType;
import com.vti.shopeebe.modal.entity.ShippingUnit;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateProductRequest {

    @NotBlank(message = "Không được bỏ trống")
    private String name;

    private String image;

    private int price;

    private ProductStatus status;

    private ShippingUnit shippingUnit;

    private ProductType type;
}
