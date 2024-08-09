package com.infofarm.common.dto;

import java.util.List;

public record PageResponseDTO<T>(
        List<T> content,
        long totalElements,
        long totalPages
) {
}
