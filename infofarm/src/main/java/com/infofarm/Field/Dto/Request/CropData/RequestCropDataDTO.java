package com.infofarm.Field.Dto.Request.CropData;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class RequestCropDataDTO {
    //TODO: implementar restricciones
    private Date planting_date;
    private Date collection_date;
    @NotNull
    private double kiloPrice;
    private double cost;
    private double kilos;
}
