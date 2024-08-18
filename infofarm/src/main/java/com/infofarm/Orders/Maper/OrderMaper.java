package com.infofarm.Orders.Maper;

import com.infofarm.Field.Mapper.CropMapper;
import com.infofarm.Orders.Dto.Response.CostumerReduced;
import com.infofarm.Orders.Dto.Response.OrderCropDataResponseDTO;
import com.infofarm.Orders.Dto.Response.OrderResponseDTO;
import com.infofarm.Orders.Models.Costumer;
import com.infofarm.Orders.Models.CropOrder;

import java.util.List;

public class OrderMaper {

    public static OrderResponseDTO createOrderResponseDTO(CropOrder order) {
        return  OrderResponseDTO.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .deliveryDate(order.getDeliveryDate())
                .paid(order.isPaid())
                .customer(createCostumerReduced(order.getCustomer()))
                .cropData(createOrderCropDataResponseDTO(order))
                .build();
    }

    private static List<OrderCropDataResponseDTO> createOrderCropDataResponseDTO(CropOrder order) {
        return order.getProducts().stream().map(product -> new OrderCropDataResponseDTO(product.getId(), product.getKilos(), CropMapper.createCropResponseReducedDTO(product.getCropData().getCrop()))).toList();
    };

    private static CostumerReduced createCostumerReduced(Costumer costumer) {
        return new CostumerReduced(costumer.getId(), costumer.getCostumerName());
    }
}
