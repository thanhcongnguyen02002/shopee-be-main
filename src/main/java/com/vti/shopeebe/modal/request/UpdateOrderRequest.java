package com.vti.shopeebe.modal.request;

import com.vti.shopeebe.modal.entity.Account;
import com.vti.shopeebe.modal.entity.Product;
import com.vti.shopeebe.modal.entity.StatusOrder;
import lombok.Data;

@Data
public class UpdateOrderRequest {

    private Account orderBy;

    private Product productId;

    private int quantity;

    private StatusOrder status;
}
