package com.infofarm.Facturation.Controller;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Facturation.Dto.Request.CreateCostumerDTO;
import com.infofarm.Facturation.Implenetation.CostumerServiceImpl;
import com.infofarm.Facturation.Models.Costumer;
import com.infofarm.Facturation.Service.CostumerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/costumer")
public class CostumerController {

    @Autowired
    private CostumerServiceImpl costumerService;

    @GetMapping("{id}")
    public Costumer getCostumerById(@PathVariable Long id) throws IdNotFoundException {
        return costumerService.getCostumer(id);
    }

    @GetMapping("all")
    public List<Costumer> getAllCostumer() {
        return costumerService.getCostumers();
    }

    @PostMapping("add-costumer")
    public Costumer addCostumer(@Valid @RequestBody CreateCostumerDTO costumer) {
        return costumerService.addCostumer(costumer);
    }

    @PutMapping("update-costumer")
    public Costumer updateCostumer(@Valid @RequestBody Costumer costumer) throws IdNotFoundException {
        return costumerService.updateCostumer(costumer);
    }

    @DeleteMapping("delete/{id}")
    public void deleteCostumer(@PathVariable Long id) throws IdNotFoundException {
        costumerService.deleteCostumer(id);
    }
}
