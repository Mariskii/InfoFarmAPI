package com.infofarm.Facturation.Dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infofarm.Facturation.Models.PurchaseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateCostDTO(
        double price,
        @NotNull PurchaseType purchaseType,
        @NotNull double quantity,
        @NotBlank String costName,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        Date buyDate
) {
}
