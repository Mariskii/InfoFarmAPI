package com.infofarm.Field.Dto.Response.Crop;

import lombok.Builder;

@Builder
public record CropResponseDTO(
        Long id,
        String cropName,
        String cropDescription,
        String cropImage
) {
}
