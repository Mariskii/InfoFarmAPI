package com.infofarm.Facturation.Service;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Facturation.Dto.Request.CreateCostumerDTO;
import com.infofarm.Facturation.Models.Costumer;

import java.util.List;

public interface CostumerService {

    List<Costumer> getCostumers();
    Costumer getCostumer(Long id) throws IdNotFoundException;
    Costumer addCostumer(CreateCostumerDTO costumer);
    Costumer updateCostumer(Costumer costumer) throws IdNotFoundException;
    void deleteCostumer(Long id);
}
