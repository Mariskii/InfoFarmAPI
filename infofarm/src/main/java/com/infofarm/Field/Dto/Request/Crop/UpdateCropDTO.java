package com.infofarm.Field.Dto.Request.Crop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record UpdateCropDTO(
        @NotNull(message = "ID is required") Long id,
        @NotBlank(message = "Crop name can't be empty") String cropName,
        @NotEmpty(message = "Crop description is required") String cropDescription,
        @NotEmpty(message = "Crop image is required") String cropImage
) {}