package com.infofarm.Users.Dto.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserDTO(@NotBlank String username,
                                @NotBlank String password,
                                @Valid AuthCreateRoleDTO roleRequest) {
}
