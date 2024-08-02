package com.infofarm.Users.Dto.Request.Auth;

import jakarta.validation.constraints.Size;

import java.util.List;

public record AuthCreateRoleDTO (@Size(max = 3, message = "User cannot have more than 3 roles") List<String> roleListName) {
}
