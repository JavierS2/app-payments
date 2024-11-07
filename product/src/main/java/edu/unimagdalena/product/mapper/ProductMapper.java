package edu.unimagdalena.product.mapper;

import edu.unimagdalena.product.dto.ProductDTO;
import edu.unimagdalena.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    public ProductDTO productToProductDTO(Product product);
    public Product productDTOToProduct(ProductDTO productDTO);
}
