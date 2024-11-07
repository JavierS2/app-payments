package edu.unimagdalena.order.mapper;

import edu.unimagdalena.order.dto.OrderDTO;
import edu.unimagdalena.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDTO(Order order);

    Order orderDTOToOrder(OrderDTO orderDTO);
}
