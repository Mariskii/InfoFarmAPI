package com.infofarm.Facturation.Implementation;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Facturation.Dto.Request.CreateCostDTO;
import com.infofarm.Facturation.Dto.Request.CreateInvoiceDTO;
import com.infofarm.Facturation.Dto.Response.MonthFacturationDTO;
import com.infofarm.Facturation.Models.Cost;
import com.infofarm.Facturation.Models.Invoice;
import com.infofarm.Facturation.Repository.CostRepository;
import com.infofarm.Facturation.Repository.InvoicesRepository;
import com.infofarm.Facturation.Service.FacturationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturationServiceImpl implements FacturationService {

    @Autowired
    CostRepository costRepository;

    @Autowired
    InvoicesRepository invoicesRepository;

    @Override
    public MonthFacturationDTO getFacturationByMonth(int month, int year) {

        return MonthFacturationDTO.builder()
                .costs(costRepository.findByBuyDateMonth(month, year))
                .invoices(invoicesRepository.findByBuyDateMonth(month, year))
                .build();
    }

    @Override
    @Transactional
    public Cost addCost(CreateCostDTO cost) {
        Cost newCost = Cost.builder()
                .quantity(cost.quantity())
                .costName(cost.costName())
                .buyDate(cost.buyDate())
                .description(cost.description())
                .price(cost.price())
                .build();

        return costRepository.save(newCost);
    }

    @Override
    public Invoice addInvoice(CreateInvoiceDTO invoice) {
        Invoice newInvoice = Invoice.builder()
                .creationBill(invoice.creationBill())
                .description(invoice.description())
                .quantity(invoice.quantity())
                .dueDate(invoice.dueDate())
                .paid(invoice.paid())
                .price(invoice.price())
                .payDate(invoice.payDate())
                .name(invoice.name())
                .build();

        return invoicesRepository.save(newInvoice);
    }

    @Override
    public Cost updateCost(Cost cost) throws IdNotFoundException{

        Cost updateCost = costRepository.findById(cost.getId()).orElseThrow(() -> new IdNotFoundException("Cost with id: "+cost.getId()+" not found"));

        updateCost.setQuantity(cost.getQuantity());
        updateCost.setCostName(cost.getCostName());
        updateCost.setDescription(cost.getDescription());
        updateCost.setPrice(cost.getPrice());
        updateCost.setBuyDate(cost.getBuyDate());

        return costRepository.save(updateCost);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) throws IdNotFoundException {

        Invoice updateInvoice = invoicesRepository.findById(invoice.getId()).orElseThrow(() -> new IdNotFoundException("Invoice with id "+invoice.getId()+" not found"));

        updateInvoice.setName(invoice.getName());
        updateInvoice.setDescription(invoice.getDescription());
        updateInvoice.setQuantity(invoice.getQuantity());
        updateInvoice.setPrice(invoice.getPrice());
        updateInvoice.setDueDate(invoice.getDueDate());
        updateInvoice.setCreationBill(invoice.getCreationBill());
        updateInvoice.setPaid(invoice.isPaid());

        return invoicesRepository.save(updateInvoice);
    }
}
