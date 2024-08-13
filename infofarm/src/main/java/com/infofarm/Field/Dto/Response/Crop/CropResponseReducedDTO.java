package com.infofarm.Field.Dto.Response.Crop;

import lombok.Builder;

@Builder
public record CropResponseReducedDTO(
        Long id,
        String cropName,
        String cropImage
) {
}
