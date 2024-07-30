package com.infofarm.Facturation.Controller;

import com.infofarm.Facturation.Dto.Request.CreateCostDTO;
import com.infofarm.Facturation.Dto.Request.CreateInvoiceDTO;
import com.infofarm.Facturation.Dto.Response.MonthFacturationDTO;
import com.infofarm.Facturation.Implementation.FacturationServiceImpl;
import com.infofarm.Facturation.Models.Cost;
import com.infofarm.Facturation.Models.Invoice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/facturation")
public class FacturationController {

    @Autowired
    private FacturationServiceImpl facturationService;

    @GetMapping("get-by-month")
    public ResponseEntity<MonthFacturationDTO> getFacturationByMonth(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity.ok(facturationService.getFacturationByMonth(month, year));
    }

    @PostMapping("add-cost")
    public ResponseEntity<Cost> addCost( @Valid @RequestBody CreateCostDTO costDTO) {
        return ResponseEntity.ok(facturationService.addCost(costDTO));
    }

    @PostMapping("add-invoice")
    public ResponseEntity<Invoice> addCost(@Valid @RequestBody CreateInvoiceDTO invoiceDTO) {
        return ResponseEntity.ok(facturationService.addInvoice(invoiceDTO));
    }
}
