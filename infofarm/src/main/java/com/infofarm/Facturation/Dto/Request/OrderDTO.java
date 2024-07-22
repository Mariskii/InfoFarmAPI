package com.infofarm.Facturation.Dto.Request;

import java.util.Date;
import java.util.List;

public record OrderDTO(
        Date orderDate,
        Date deliveryDate,
        boolean paid,
        boolean delivered,
        Long customerId,
        List<CropDataOrderDTO> products
) {}
