package com.vti.shopeebe.repository.specification;

import com.vti.shopeebe.modal.entity.Product;
import com.vti.shopeebe.modal.entity.ProductStatus;
import com.vti.shopeebe.modal.entity.ProductType;
import com.vti.shopeebe.modal.entity.ShippingUnit;
import com.vti.shopeebe.modal.request.SearchProductRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class ProductSpecification {

    public static Specification<Product> buildCondition(SearchProductRequest request){
        return Specification.where(byProductName(request.getProductName()))
                .and(byProductType(request.getProductTypes()))
                .and(byShippingUnit(request.getShippingUnits()))
                .and(byProductStatus(request.getProductStatus()))
                .and(byPriceMin(request.getMinPrice()))
                .and(byPriceMax(request.getMaxPrice()));
    }

    private static Specification<Product> byProductName(String productname){
        if (productname != null ){
            return (root, query, cri) -> {
                return cri.like(root.get("name"), "%" + productname + "%");
            };
        } else {
            return null;
        }
    }

    private static Specification<Product> byProductType(Set<ProductType> productTypes){
        if (productTypes != null && productTypes.size() > 0) {
            return (root, query, cri) -> {
                return root.get("type").in(productTypes);
            };
        }
        return null;
    }

    private static Specification<Product> byShippingUnit(Set<ShippingUnit> shippingUnits){
        if (shippingUnits != null && shippingUnits.size() > 0) {
            return (root, query, cri) -> {
                return root.get("shippingUnit").in(shippingUnits);
            };
        }
        return null;
    }

    private static Specification<Product> byProductStatus(Set<ProductStatus> productStatus){
        if (productStatus != null && productStatus.size() > 0) {
            return (root, query, cri) -> {
                return root.get("status").in(productStatus);
            };
        }
        return null;
    }

    private static Specification<Product> byPriceMin(Long minPrice) {
        if (minPrice != null) {
            return (root, query, cri) -> {
                return cri.greaterThanOrEqualTo(root.get("price"), minPrice);
            };
        }
        return null;
    }

    private static Specification<Product> byPriceMax(Long maxPrice) {
        if (maxPrice != null) {
            return (root, query, cri) -> {
                return cri.lessThanOrEqualTo(root.get("price"), maxPrice);
            };
        }
        return null;
    }
}
