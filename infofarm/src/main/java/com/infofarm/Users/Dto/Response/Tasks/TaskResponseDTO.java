package com.infofarm.Users.Dto.Response.Tasks;

import lombok.Builder;

import java.util.Date;

@Builder
public record TaskResponseDTO(
        Long id,
        String name,
        String description,
        String priority,
        boolean completed,
        Date limit_date
) {
}
