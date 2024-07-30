package com.infofarm.Facturation.Implementation;

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

import java.util.Calendar;
import java.util.Date;

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
                .purchaseType(cost.purchaseType())
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
}
