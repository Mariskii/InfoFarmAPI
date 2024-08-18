package com.infofarm.Orders.Controller;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Exception.Errors.NotEnoughtQuantityException;
import com.infofarm.Orders.Dto.Request.OrderDTO;
import com.infofarm.Orders.Dto.Request.UpdateOrderProductDTO;
import com.infofarm.Orders.Dto.Response.OrderResponseDTO;
import com.infofarm.Orders.Implenetation.OrderServiceImpl;

import com.infofarm.Orders.Models.CropOrder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderDTO order) throws IdNotFoundException, NotEnoughtQuantityException {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PutMapping("{id}/update-order")
    public CropOrder updateOrder(@Valid @RequestBody OrderDTO order, @PathVariable Long id) throws IdNotFoundException, NotEnoughtQuantityException {
        return orderService.updateOrder(order, id);
    }

    @PutMapping("update-order-product")
    public boolean updateOrderProducts(@Valid @RequestBody UpdateOrderProductDTO order) throws IdNotFoundException, NotEnoughtQuantityException {
        return orderService.updateOrderProduct(order);
    }

    @DeleteMapping("{id}/delete")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
