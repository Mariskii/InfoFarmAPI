package com.infofarm.Facturation.Service;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Exception.Errors.NotEnoughtQuantityException;
import com.infofarm.Facturation.Dto.Request.CropDataOrderDTO;
import com.infofarm.Facturation.Dto.Request.OrderDTO;
import com.infofarm.Facturation.Dto.Request.UpdateOrderProductDTO;
import com.infofarm.Facturation.Models.CropOrder;

import java.util.List;

public interface OrderService {
    CropOrder createOrder(OrderDTO order) throws IdNotFoundException, NotEnoughtQuantityException;
    CropOrder updateOrder(OrderDTO cropOrder,Long id) throws IdNotFoundException;
    boolean updateOrderProduct(UpdateOrderProductDTO updatedProduct) throws IdNotFoundException, NotEnoughtQuantityException;
    void deleteOrder(Long id);
    CropOrder getOrder(Long id) throws IdNotFoundException;
}
