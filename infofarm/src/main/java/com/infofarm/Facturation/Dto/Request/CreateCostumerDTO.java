package com.infofarm.Facturation.Dto.Request;

import jakarta.validation.constraints.NotBlank;

public record CreateCostumerDTO (
        @NotBlank String costumerName,
        String costumerEmail,
        String costumerPhone,
        String costumerAddress,
        String costumerCity,
        String costumerPostalCode
) {
}
