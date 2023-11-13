package com.vti.shopeebe.modal.request;

import com.vti.shopeebe.validate.ProductIdExists;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CreateOrderRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private int accountId;

    @ProductIdExists()
    private int productId;

    private int quantity;

}
