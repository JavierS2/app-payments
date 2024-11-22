package edu.unimagdalena.user.controller;

import edu.unimagdalena.user.entity.UserDTO;
import edu.unimagdalena.user.exception.ResourceNotAbleToDeleteException;
import edu.unimagdalena.user.exception.ResourceNotFoundException;
import edu.unimagdalena.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController

@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserDTO user = userService.update(id, userDTO);
            return ResponseEntity.ok().body(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotAbleToDeleteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        try {
            UserDTO user = userService.getById(id);
            return ResponseEntity.ok().body(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
