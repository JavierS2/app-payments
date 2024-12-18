package edu.unimagdalena.order.service;

import edu.unimagdalena.order.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO postOrder(OrderDTO OrderDTO);
    OrderDTO getOrderById(Long id);
    OrderDTO putOrder(OrderDTO OrderDTO, Long id);
    OrderDTO patchOrder(OrderDTO OrderDTO, Long id);
    void deleteOrder(Long id);
    List<OrderDTO> getAll();

}
