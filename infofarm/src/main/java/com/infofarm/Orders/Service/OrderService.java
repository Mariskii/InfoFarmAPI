package com.infofarm.Orders.Service;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Exception.Errors.NotEnoughtQuantityException;
import com.infofarm.Orders.Dto.Request.CropDataOrderDTO;
import com.infofarm.Orders.Dto.Request.OrderDTO;
import com.infofarm.Orders.Dto.Request.UpdateOrderProductDTO;
import com.infofarm.Orders.Dto.Response.OrderResponseDTO;
import com.infofarm.Orders.Models.CropOrder;

public interface OrderService {
    OrderResponseDTO createOrder(OrderDTO order) throws IdNotFoundException, NotEnoughtQuantityException;
    CropOrder updateOrder(OrderDTO cropOrder,Long id) throws IdNotFoundException;
    boolean updateOrderProduct(UpdateOrderProductDTO updatedProduct) throws IdNotFoundException, NotEnoughtQuantityException;
    void deleteOrder(Long id);
    CropOrder getOrder(Long id) throws IdNotFoundException;
}
