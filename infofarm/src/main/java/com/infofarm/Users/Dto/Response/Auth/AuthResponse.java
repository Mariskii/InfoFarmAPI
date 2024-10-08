package com.infofarm.Users.Dto.Response.Auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message", "accessToken"})
public record AuthResponse(String status,
                           String message,
                           String accessToken) {
}
