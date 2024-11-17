package edu.unimagdalena.product.dto;

public record ProductDTO(
        Long id,
        String name,
        String description,
        String price,
        Integer stock
) { }
