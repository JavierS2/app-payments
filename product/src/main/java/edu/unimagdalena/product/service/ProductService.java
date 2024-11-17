package edu.unimagdalena.product.service;

import edu.unimagdalena.product.dto.ProductDTO;

public interface ProductService {

    public ProductDTO getProductById(Long id);
    public ProductDTO saveProduct(ProductDTO productoDTO);
    public ProductDTO updateProduct(Long id, ProductDTO detallesProduct);
    public void deleteProductById(Long id);

}
