package com.infofarm.Orders.Dto.Response;

import com.infofarm.Field.Dto.Response.CropData.CropDataResponseDTO;
import com.infofarm.Orders.Models.Costumer;
import com.infofarm.Orders.Models.OrderCropData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
public record OrderResponseDTO(
        Long id,
        Date orderDate,
        Date deliveryDate,
        boolean paid,
        boolean delivered,
        CostumerReduced customer,
        List<OrderCropDataResponseDTO> cropData
) {
}
