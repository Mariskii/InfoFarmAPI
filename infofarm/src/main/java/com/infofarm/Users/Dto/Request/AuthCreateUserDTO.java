package com.infofarm.Users.Dto.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record AuthCreateUserDTO(@NotBlank String username,
                                @NotBlank String password,
                                @Valid AuthCreateRoleDTO roleRequest) {
}
