package edu.unimagdalena.order.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private String status;
}