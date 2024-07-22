package com.infofarm.Bussines.Dto.Response;

import lombok.Builder;

import java.util.Date;

@Builder
public record BussinesResponseDTO(
        Long id,
        String name,
        String logoURL
) {
}
