package com.vti.shopeebe.modal.request;

import com.vti.shopeebe.modal.dto.BaseRequest;
import com.vti.shopeebe.modal.entity.ProductStatus;
import com.vti.shopeebe.modal.entity.ProductType;
import com.vti.shopeebe.modal.entity.ShippingUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductRequest extends BaseRequest {

    private String productName;

    private Set<ProductType> productTypes;

    private Set<ShippingUnit> shippingUnits;

    private Set<ProductStatus> productStatus;

    private Long minPrice;

    private Long maxPrice;

}
