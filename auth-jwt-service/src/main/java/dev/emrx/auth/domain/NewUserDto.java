package dev.emrx.auth.domain;

public record NewUserDto(
        String username,
        String password,
        String role
) {
}
