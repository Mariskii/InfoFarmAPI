package com.infofarm.Field.Dto.Response.Crop;

import lombok.Builder;

@Builder
public record CropResponseReducedDTO(
        String cropName,
        String cropImage
) {
}
