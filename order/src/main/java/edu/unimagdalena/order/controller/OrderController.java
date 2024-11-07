package edu.unimagdalena.order.controller;

import edu.unimagdalena.order.dto.OrderDTO;
import edu.unimagdalena.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> postOrder(@RequestBody OrderDTO orderDTO) { //
        try {
            return ResponseEntity.ok(orderService.postOrder(orderDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Se ha producido un error creando la order.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) { //
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (Exception IllegalArgumentException) {
            return ResponseEntity.status(404).body("La id " + id + " no ha sido encontrada.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putOrder(@RequestBody OrderDTO orderDTO, @PathVariable("id") Long id) { //
        try {
            return ResponseEntity.status(204).body(orderService.putOrder(orderDTO, id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Ha ocurrido un error actualizando la order.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchOrder(@RequestBody OrderDTO orderDTO, @PathVariable("id") Long id) { //
        try {
            return ResponseEntity.status(204).body(orderService.patchOrder(orderDTO, id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Ha ocurrido un error modificando algunos datos de la order.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) { //
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Ha ocurrido un error eliminando la order.");
        }
    }
}

