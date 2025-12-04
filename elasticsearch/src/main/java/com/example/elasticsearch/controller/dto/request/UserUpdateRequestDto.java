package com.example.elasticsearch.controller.dto.request;

public record UserUpdateRequestDto(
        String name,
        Long age,
        Boolean isActive
) {
}
