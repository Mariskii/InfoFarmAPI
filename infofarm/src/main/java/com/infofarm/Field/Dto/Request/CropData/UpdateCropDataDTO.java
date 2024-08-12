package com.infofarm.Field.Dto.Request.CropData;

import java.util.Date;

public record UpdateCropDataDTO(
        Long id,
        Date planting_date,
        Date collection_date,
        double kiloPrice,
        double cost,
        double kilos
) {
}
