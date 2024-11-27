package edu.unimagdalena.order.service;

import edu.unimagdalena.order.dto.OrderDTO;
import edu.unimagdalena.order.entity.Order;
import edu.unimagdalena.order.mapper.OrderMapper;
import edu.unimagdalena.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO postOrder(OrderDTO OrderDTO) {
        if (OrderDTO == null) {
            throw new IllegalArgumentException("El objeto OrderDTO no puede ser null.");
        }
        try {
            Order order = OrderMapper.INSTANCE.orderDTOToOrder(OrderDTO);
            order = orderRepository.save(order);
            return OrderMapper.INSTANCE.orderToOrderDTO(order);
        } catch (Exception e) {
            throw new IllegalStateException("Se ha producido un error creando la Order.", e);
        }
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        try {
            if (!orderRepository.existsById(id)) {
                throw new IllegalArgumentException("La Order no ha sido encontrada.");
            }
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("La Order no ha sido encontrada."));
            return OrderMapper.INSTANCE.orderToOrderDTO(order);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Se ha producido un error obteniendo la Order.");
        }
    }

    @Override
    public OrderDTO putOrder(OrderDTO OrderDTO, Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("El ID de la Order no puede ser nulo.");
            }
            Order orderFromDB = orderRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Order not found"));
            orderFromDB.setProductId(OrderDTO.productId());
            orderFromDB.setQuantity(OrderDTO.quantity());
            orderFromDB.setStatus(OrderDTO.status());
            orderFromDB.setUserId(OrderDTO.userId());
            Order savedOrder = orderRepository.save(orderFromDB);
            return OrderMapper.INSTANCE.orderToOrderDTO(savedOrder);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Se ha producido un error creando la Order.");
        }
    }

    @Override
    public OrderDTO patchOrder(OrderDTO OrderDTO, Long id) {
        try {

            Order Order = orderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("La Order con ID " + id + " no existe."));
            if (OrderDTO.productId() != null) {
                Order.setProductId(OrderDTO.productId());
            }
            if (OrderDTO.quantity() != null) {
                Order.setQuantity(OrderDTO.quantity());
            }
            if (OrderDTO.status() != null) {
                Order.setStatus(OrderDTO.status());
            }
            if (OrderDTO.userId() != null) {
                Order.setUserId(OrderDTO.userId());
            }
            Order = orderRepository.save(Order);
            return OrderMapper.INSTANCE.orderToOrderDTO(Order);
        } catch (Exception e) {
            throw new EntityNotFoundException("La Order con ID " + id + " no existe.");
        }

    }

    @Override
    public void deleteOrder(Long id) {
        try {
            if (orderRepository.existsById(id)) {
                orderRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("La Order con ID " + id + " no existe.");
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Se ha producido un error eliminando la Order.");
        }
    }

    @Override
    public List<OrderDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper.INSTANCE::orderToOrderDTO)
                .toList();
    }
}

