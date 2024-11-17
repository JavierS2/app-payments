package edu.unimagdalena.order.dto;

public record OrderDTO (
        Long id,
        Long userId,
        Long productId,
        Integer quantity,
        String status
) { }