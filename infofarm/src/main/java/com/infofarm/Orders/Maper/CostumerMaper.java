package com.infofarm.Orders.Maper;

import com.infofarm.Orders.Dto.Response.CostumerReduced;
import com.infofarm.Orders.Models.Costumer;

public class CostumerMaper {

    public static CostumerReduced convertToCostumerReducedDTO(Costumer costumer) {
        return new CostumerReduced(costumer.getId(), costumer.getCostumerName());
    }
}
