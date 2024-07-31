package com.infofarm.Facturation.Service;

import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Facturation.Dto.Request.CreateCostDTO;
import com.infofarm.Facturation.Dto.Request.CreateInvoiceDTO;
import com.infofarm.Facturation.Dto.Response.MonthFacturationDTO;
import com.infofarm.Facturation.Models.Cost;
import com.infofarm.Facturation.Models.Invoice;

public interface FacturationService {
    MonthFacturationDTO getFacturationByMonth(int month, int year);

    Cost addCost(CreateCostDTO cost);

    Invoice addInvoice(CreateInvoiceDTO cost);

    Cost updateCost(Cost cost) throws IdNotFoundException;

    Invoice updateInvoice(Invoice invoice) throws IdNotFoundException;
}
