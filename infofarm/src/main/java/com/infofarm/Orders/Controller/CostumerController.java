package com.infofarm.Orders.Controller;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Orders.Dto.Request.CreateCostumerDTO;
import com.infofarm.Orders.Implenetation.CostumerServiceImpl;
import com.infofarm.Orders.Models.Costumer;
import com.infofarm.common.dto.PageResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("get-by-name")
    public PageResponseDTO<Costumer> getCostumerByName(@RequestParam String name,
                                                       @PageableDefault(size = 5) Pageable pageable) throws IdNotFoundException {
        return costumerService.getCostumersByName(name.toLowerCase(), pageable);
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
