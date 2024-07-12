package com.infofarm.Field.Dto.Request.CropNeeds;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCropNeedDTO {
    @NotBlank
    private String needName;
    private String description;
}
