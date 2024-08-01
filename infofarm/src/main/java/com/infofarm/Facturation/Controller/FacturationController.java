package com.infofarm.Facturation.Controller;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Facturation.Dto.Request.CreateCostDTO;
import com.infofarm.Facturation.Dto.Request.CreateInvoiceDTO;
import com.infofarm.Facturation.Dto.Response.MonthFacturationDTO;
import com.infofarm.Facturation.Implementation.FacturationServiceImpl;
import com.infofarm.Facturation.Models.Cost;
import com.infofarm.Facturation.Models.Invoice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/facturation")
@PreAuthorize("hasRole('BOSS')")
public class FacturationController {

    @Autowired
    private FacturationServiceImpl facturationService;

    @GetMapping("get-by-month")
    public ResponseEntity<MonthFacturationDTO> getFacturationByMonth(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity.ok(facturationService.getFacturationByMonth(month, year));
    }

    @PostMapping("add-cost")
    public ResponseEntity<Cost> addCost( @Valid @RequestBody CreateCostDTO costDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facturationService.addCost(costDTO));
    }

    @PostMapping("add-invoice")
    public ResponseEntity<Invoice> addCost(@Valid @RequestBody CreateInvoiceDTO invoiceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facturationService.addInvoice(invoiceDTO));
    }

    @PutMapping("update-invoice")
    public ResponseEntity<Invoice> updateInvoice(@Valid @RequestBody Invoice invoice) throws IdNotFoundException {
        return ResponseEntity.ok(facturationService.updateInvoice(invoice));
    }

    @PutMapping("update-cost")
    public ResponseEntity<Cost> updateInvoice(@Valid @RequestBody Cost cost) throws IdNotFoundException {
        return ResponseEntity.ok(facturationService.updateCost(cost));
    }
}
