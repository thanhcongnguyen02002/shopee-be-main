package com.vti.shopeebe.repository.specification;

import com.vti.shopeebe.modal.entity.Account;
import com.vti.shopeebe.modal.entity.Order;
import com.vti.shopeebe.modal.entity.StatusOrder;
import com.vti.shopeebe.modal.request.SearchOrderRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class OrderSpecification {

    public static Specification<Order> buildCondition(SearchOrderRequest request) {
        return Specification.where(byOrderBy(request.getOrderBy()))
                .and(byStatusOrder(request.getStatusOrder()));
    }

    private static Specification<Order> byOrderBy(int accountId) {
        if (accountId > 0) {
            return (root, query, cri) -> {
                Join<Order, Account> joiner = root.join("orderBy");
                return cri.equal(joiner.get("id"), accountId);
            };
        }
        return null;
    }

    private static Specification<Order> byStatusOrder(StatusOrder statusOrder) {
        if (statusOrder != null) {
            return (root, query, cri) -> {
                return cri.equal(root.get("status"), statusOrder);
            };
        }
        return null;
    }
}
