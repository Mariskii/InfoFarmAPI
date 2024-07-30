package com.infofarm.Facturation.Dto.Request;

import java.util.Date;

public record CreateInvoiceDTO(
        String name,
        String description,
        boolean paid,
        Date creationBill,
        Date payDate,
        Date dueDate,
        double price,
        double quantity
) {
}
