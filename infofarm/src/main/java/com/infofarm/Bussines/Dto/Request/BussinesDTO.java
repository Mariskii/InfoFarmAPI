package com.infofarm.Bussines.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class BussinesDTO {
    @NotBlank
    private String name;

    private String logoURL;
}
