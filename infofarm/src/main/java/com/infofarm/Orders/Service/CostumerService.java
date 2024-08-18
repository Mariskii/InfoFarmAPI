package com.infofarm.Orders.Service;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Orders.Dto.Request.CreateCostumerDTO;
import com.infofarm.Orders.Models.Costumer;
import com.infofarm.common.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CostumerService {

    List<Costumer> getCostumers();
    Costumer getCostumer(Long id) throws IdNotFoundException;
    PageResponseDTO<Costumer> getCostumersByName(String name, Pageable pageable) throws IdNotFoundException;
    Costumer addCostumer(CreateCostumerDTO costumer);
    Costumer updateCostumer(Costumer costumer) throws IdNotFoundException;
    void deleteCostumer(Long id);
}
