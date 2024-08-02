package com.infofarm.Users.Dto.Request.Tasks;

import java.util.Date;

public record TaskRequesteDTO(
        String name,
        String description,
        String priority,
        Date limit_date,
        boolean completed,
        Long userId
) {
}
