package com.vti.shopeebe.modal.request;

import com.vti.shopeebe.modal.dto.BaseRequest;
import com.vti.shopeebe.modal.entity.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchOrderRequest extends BaseRequest {

    private int orderBy;

    private StatusOrder statusOrder;

}
