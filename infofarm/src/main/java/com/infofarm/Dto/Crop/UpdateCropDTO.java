package com.infofarm.Dto.Crop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateCropDTO {

    @NotNull(message = "ID is required")
    private Long id;

    @NotBlank(message = "Crop name cant be empty")
    private String cropName;

    @NotEmpty(message = "Crop description is required")
    private String cropDescription;

    @NotEmpty(message = "Crop image is required")
    private String cropImage;
}
