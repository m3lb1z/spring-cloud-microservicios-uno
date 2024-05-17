package dev.emrx.gateway.domain;

public record RequestDto(
        String uri,
        String method) {
}
