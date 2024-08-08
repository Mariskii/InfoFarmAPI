package com.infofarm.Field.Dto.Response.Plantation;

import lombok.Builder;

@Builder
public record PlantationResponseDTO(
        Long id,
        String name,
        String description,
        String location
) {
}
