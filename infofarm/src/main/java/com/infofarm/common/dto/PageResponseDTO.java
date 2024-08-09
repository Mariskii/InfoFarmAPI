package com.infofarm.common.dto;

import lombok.Builder;

import java.util.List;

public record PageResponseDTO<T>(
        List<T> content,
        int totalPages
) {
}
