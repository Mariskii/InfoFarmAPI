package com.infofarm.Facturation.Dto.Response;

import com.infofarm.Facturation.Models.Cost;
import com.infofarm.Facturation.Models.Invoice;
import lombok.Builder;

import java.util.List;

@Builder
public record MonthFacturationDTO(
        List<Cost> costs,
        List<Invoice> invoices
) {

}
