package com.infofarm.Field.Dto.Request.CropData;

import java.util.Date;

public record UpdateCropDataDTO(
        Date planting_date,
        Date collection_date,
        double kilo_price,
        double cost,
        double kilos
) {
}
