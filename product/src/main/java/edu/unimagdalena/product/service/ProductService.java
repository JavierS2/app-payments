package edu.unimagdalena.product.service;

import edu.unimagdalena.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO getProductById(Long id);
    ProductDTO saveProduct(ProductDTO productoDTO);
    ProductDTO updateProduct(Long id, ProductDTO detallesProduct);
    void deleteProductById(Long id);
    List<ProductDTO> getAllProducts();

}
