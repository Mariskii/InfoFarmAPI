package com.infofarm.Facturation.Controller;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Exception.Errors.NotEnoughtQuantityException;
import com.infofarm.Facturation.Dto.Request.CropDataOrderDTO;
import com.infofarm.Facturation.Dto.Request.OrderDTO;
import com.infofarm.Facturation.Dto.Request.UpdateOrderProductDTO;
import com.infofarm.Facturation.Implenetation.OrderServiceImpl;

import com.infofarm.Facturation.Models.CropOrder;
import com.infofarm.Facturation.Models.OrderCropData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("{id}")
    public CropOrder getOrderById(@PathVariable Long id) throws IdNotFoundException {
        return orderService.getOrder(id);
    }

    @PostMapping("create-order")
    public CropOrder createOrder(@Valid @RequestBody OrderDTO order) throws IdNotFoundException, NotEnoughtQuantityException {
        return orderService.createOrder(order);
    }

    @PutMapping("{id}/update-order")
    public CropOrder updateOrder(@Valid @RequestBody OrderDTO order, @PathVariable Long id) throws IdNotFoundException, NotEnoughtQuantityException {
        return orderService.updateOrder(order, id);
    }

    @PutMapping("/update-product")
    public boolean updateOrderProducts(@Valid @RequestBody UpdateOrderProductDTO order) throws IdNotFoundException, NotEnoughtQuantityException {
        return orderService.updateOrderProduct(order);
    }

    @DeleteMapping("{id}/delete")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    //TODO: Endpoint para añadir el cobro a facturación
}
