package com.infofarm.Dto.Request.CropNeeds;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCropNeedDTO {

    @NotBlank
    private String needName;
    private String description;
}
