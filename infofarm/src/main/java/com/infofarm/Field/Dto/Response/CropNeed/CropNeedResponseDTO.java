package com.infofarm.Field.Dto.Response.CropNeed;

import lombok.Builder;

@Builder
public record CropNeedResponseDTO(
        String needName,
        String description
) {
}
