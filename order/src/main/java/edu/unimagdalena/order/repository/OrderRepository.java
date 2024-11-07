package edu.unimagdalena.order.repository;

import edu.unimagdalena.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
