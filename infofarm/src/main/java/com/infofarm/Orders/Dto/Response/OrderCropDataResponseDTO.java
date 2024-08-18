package com.infofarm.Orders.Dto.Response;

import com.infofarm.Field.Dto.Response.Crop.CropResponseReducedDTO;

public record OrderCropDataResponseDTO(
        Long id,
        double kilos,
        CropResponseReducedDTO crop
) {
}
