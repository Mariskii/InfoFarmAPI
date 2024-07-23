package com.infofarm.Field.Dto.Request.Crop;

import jakarta.validation.constraints.NotEmpty;

public record CreateCropDTO(
        @NotEmpty(message = "crop name is required")
        String cropName,
        String cropDescription
) { }
