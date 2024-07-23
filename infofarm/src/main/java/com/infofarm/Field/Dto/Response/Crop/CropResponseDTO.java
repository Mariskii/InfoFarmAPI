package com.infofarm.Field.Dto.Response.Crop;

import lombok.Builder;

@Builder
public record CropResponseDTO(
        String cropName,
        String cropDescription,
        String imageURL
) {
}
