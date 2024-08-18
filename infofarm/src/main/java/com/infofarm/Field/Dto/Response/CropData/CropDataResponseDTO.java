package com.infofarm.Field.Dto.Response.CropData;

import com.infofarm.Field.Dto.Response.Crop.CropResponseDTO;
import com.infofarm.Field.Dto.Response.Crop.CropResponseReducedDTO;
import com.infofarm.Field.Dto.Response.CropNeed.CropNeedResponseDTO;
import com.infofarm.Field.Models.TypeSurface;
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
        double surface,
        double kilos_surface,
        TypeSurface type_surface,
        CropResponseReducedDTO crop
) {
}
