package edu.unimagdalena.user.entity;

public record UserDTO (
    Long id,
    String username,
    String email,
    String password
) { }
