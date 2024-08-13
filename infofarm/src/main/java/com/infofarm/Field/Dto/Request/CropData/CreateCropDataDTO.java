package com.infofarm.Field.Dto.Request.CropData;

import lombok.Builder;

import java.util.Date;

@Builder
public record CreateCropDataDTO(
        Long id,
        Date planting_date,
        Date collection_date,
        double kilo_price,
        double cost,
        double kilos
) {

}
