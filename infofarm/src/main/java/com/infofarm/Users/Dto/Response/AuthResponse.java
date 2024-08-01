package com.infofarm.Users.Dto.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message", "accessToken"})
public record AuthResponse(String message,
                           String accessToken) {
}
