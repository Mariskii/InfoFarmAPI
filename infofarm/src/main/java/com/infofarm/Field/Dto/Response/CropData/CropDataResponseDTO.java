package com.infofarm.Field.Dto.Response.CropData;

import com.infofarm.Field.Dto.Response.Crop.CropResponseDTO;
import com.infofarm.Field.Dto.Response.CropNeed.CropNeedResponseDTO;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record CropDataResponseDTO(
        Long id,
        Date planting_date,
        Date collection_date,
        double kilo_price,
        double cost,
        double kilos,
        CropResponseDTO crop,
        List<CropNeedResponseDTO> needs
) {
}
