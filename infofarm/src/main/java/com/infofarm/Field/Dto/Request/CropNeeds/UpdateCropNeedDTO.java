package com.infofarm.Field.Dto.Request.CropNeeds;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCropNeedDTO {

    @NotBlank
    private String needName;
    private String description;
}
