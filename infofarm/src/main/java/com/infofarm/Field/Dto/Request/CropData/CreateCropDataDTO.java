package com.infofarm.Field.Dto.Request.CropData;

import com.infofarm.Field.Models.TypeSurface;
import lombok.Builder;

import java.util.Date;

@Builder
public record CreateCropDataDTO(
        Long id,
        Date planting_date,
        Date collection_date,
        double kilo_price,
        TypeSurface type_surface,
        double surface,
        double cost,
        double kilos
) {

}
