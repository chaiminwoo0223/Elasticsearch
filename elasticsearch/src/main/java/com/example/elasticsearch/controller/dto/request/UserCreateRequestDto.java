package com.example.elasticsearch.controller.dto.request;

public record UserCreateRequestDto(
        String id,
        String name,
        Long age,
        Boolean isActive
) {
}
