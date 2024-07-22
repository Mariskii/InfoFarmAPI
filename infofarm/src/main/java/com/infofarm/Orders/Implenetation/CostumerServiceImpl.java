package com.infofarm.Orders.Implenetation;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Orders.Dto.Request.CreateCostumerDTO;
import com.infofarm.Orders.Models.Costumer;
import com.infofarm.Orders.Repository.CostumerRepository;
import com.infofarm.Orders.Service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostumerServiceImpl implements CostumerService {

    @Autowired
    private CostumerRepository costumerRepository;

    @Override
    public List<Costumer> getCostumers() {
        return (List<Costumer>) costumerRepository.findAll();
    }

    @Override
    public Costumer getCostumer(Long id) throws IdNotFoundException {
        return costumerRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Costumer not found"));
    }

    @Override
    public Costumer addCostumer(CreateCostumerDTO costumer) {

        Costumer newCostumer = Costumer.builder()
                .costumerName(costumer.costumerName())
                .costumerEmail(costumer.costumerEmail())
                .costumerAddress(costumer.costumerAddress())
                .costumerPhone(costumer.costumerPhone())
                .build();

        return costumerRepository.save(newCostumer);
    }

    @Override
    public Costumer updateCostumer(Costumer costumer) throws IdNotFoundException{
        Costumer actualCostumer = costumerRepository.findById(costumer.getId()).orElseThrow(() -> new IdNotFoundException("Costumer not found"));

        actualCostumer.setCostumerAddress(costumer.getCostumerAddress());
        actualCostumer.setCostumerName(costumer.getCostumerName());
        actualCostumer.setCostumerPhone(costumer.getCostumerPhone());
        actualCostumer.setCostumerEmail(costumer.getCostumerEmail());
        actualCostumer.setCostumerAddress(costumer.getCostumerAddress());
        actualCostumer.setCostumerCity(costumer.getCostumerCity());

        costumerRepository.save(actualCostumer);
        return actualCostumer;
    }

    @Override
    public void deleteCostumer(Long id){
        costumerRepository.deleteById(id);
    }
}
