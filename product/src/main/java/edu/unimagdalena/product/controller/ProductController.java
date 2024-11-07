package edu.unimagdalena.product.controller;

import edu.unimagdalena.product.dto.ProductDTO;
import edu.unimagdalena.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error inesperado.");
        }
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody ProductDTO productDTO) { // ya funciona
        try {
            productService.saveProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("El product ha sido guardado.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage()); // error 422
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putProductById(@PathVariable("id") Long id, @RequestBody ProductDTO detallesproduct) {
        try {
            productService.updateProduct(id, detallesproduct);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha podido actualizar el product con la ID: " + id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception EntityNotFoundException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityNotFoundException.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha podido eliminar el product con la ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}

