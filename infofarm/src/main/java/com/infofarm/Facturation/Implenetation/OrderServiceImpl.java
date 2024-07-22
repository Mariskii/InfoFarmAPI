package com.infofarm.Facturation.Implenetation;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Exception.Errors.NotEnoughtQuantityException;
import com.infofarm.Facturation.Dto.Request.CropDataOrderDTO;
import com.infofarm.Facturation.Dto.Request.OrderDTO;
import com.infofarm.Facturation.Dto.Request.UpdateOrderProductDTO;
import com.infofarm.Facturation.Models.CropOrder;
import com.infofarm.Facturation.Models.OrderCropData;
import com.infofarm.Facturation.Repository.CostumerRepository;
import com.infofarm.Facturation.Repository.OrderCropDataRepository;
import com.infofarm.Facturation.Repository.OrderRepository;
import com.infofarm.Facturation.Service.OrderService;
import com.infofarm.Field.Models.CropData;
import com.infofarm.Field.Repository.CropDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderCropDataRepository orderCropDataRepository;

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private CropDataRepository cropDataRepository;

    @Override
    @Transactional
    public CropOrder createOrder(OrderDTO order) throws IdNotFoundException, NotEnoughtQuantityException {
        CropOrder newCropOrder = CropOrder.builder()
                .orderDate(order.orderDate())
                .customer(costumerRepository.findById(order.customerId())
                        .orElseThrow(() -> new IdNotFoundException("Costumer with id "+order.customerId()+" not found")))
                .delivered(order.delivered())
                .paid(order.paid())
                .deliveryDate(order.deliveryDate())
                .build();

        newCropOrder = orderRepository.save(newCropOrder);

        addCropToOrder(order, newCropOrder);

        return newCropOrder;
    }

    private void addCropToOrder(OrderDTO order, CropOrder newCropOrder) throws IdNotFoundException, NotEnoughtQuantityException {

        for (CropDataOrderDTO cropData : order.products()){

            CropData crop = cropDataRepository.findById(cropData.cropDataId()).orElseThrow(() -> new IdNotFoundException("CropData with id "+cropData.cropDataId()+" not found"));

            if (crop.getKilos() - cropData.kilos() < 0) {
                throw new NotEnoughtQuantityException("Not enough kilos available");
            }

            crop.setKilos(crop.getKilos() - cropData.kilos());

            cropDataRepository.save(crop);

            OrderCropData orderCropData = OrderCropData.builder()
                    .cropData(crop)
                    .cropOrder(newCropOrder)
                    .kilos(cropData.kilos())
                    .price(crop.getKilo_price())
                    .build();

            orderCropDataRepository.save(orderCropData);

            if(newCropOrder.getProducts() == null) {
                newCropOrder.setProducts(new ArrayList<>());
                newCropOrder.getProducts().add(orderCropData);
            } else {
                newCropOrder.getProducts().add(orderCropData);
            }
        }
    }

    @Override
    public CropOrder updateOrder(OrderDTO cropOrder, Long id) throws IdNotFoundException {
        CropOrder actualCropOrder = orderRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Order not found with that id"));

        actualCropOrder.setCustomer(costumerRepository.findById(cropOrder.customerId())
                .orElseThrow(() -> new IdNotFoundException("Costumer with id "+cropOrder.customerId()+" not found")));
        actualCropOrder.setOrderDate(cropOrder.orderDate());
        actualCropOrder.setDelivered(cropOrder.delivered());
        actualCropOrder.setPaid(cropOrder.paid());

        orderRepository.save(actualCropOrder);

        return actualCropOrder;
    }

    @Override
    @Transactional
    public boolean updateOrderProduct(UpdateOrderProductDTO newOrderData) throws IdNotFoundException, NotEnoughtQuantityException {
        OrderCropData actualOrderData = orderCropDataRepository.findById(newOrderData.id())
                .orElseThrow(() -> new IdNotFoundException("OrderCropData with id "+newOrderData.id()+" not found"));

        CropData actualCropData = actualOrderData.getCropData();

        if((actualCropData.getKilos() + (actualOrderData.getKilos() - newOrderData.kilos())) >= 0) {
            actualCropData.setKilos(actualCropData.getKilos() + (actualOrderData.getKilos() - newOrderData.kilos()));
            actualOrderData.setKilos(newOrderData.kilos());
        } else {
            throw new NotEnoughtQuantityException("Not enough kilos available");
        }

        cropDataRepository.save(actualCropData);
        orderCropDataRepository.save(actualOrderData);

        return true;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public CropOrder getOrder(Long id) throws IdNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new IdNotFoundException("There is no order with the id "+id));
    }
}
