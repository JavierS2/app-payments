package edu.unimagdalena.product.service;

import edu.unimagdalena.product.dto.ProductDTO;
import edu.unimagdalena.product.entity.Product;
import edu.unimagdalena.product.mapper.ProductMapper;
import edu.unimagdalena.product.repository.ProductRepository;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    private final RepositoryMethodInvocationListener repositoryMethodInvocationListener;

    public ProductServiceImpl(ProductRepository productRepository, RepositoryMethodInvocationListener repositoryMethodInvocationListener) {
        this.productRepository = productRepository;
        this.repositoryMethodInvocationListener = repositoryMethodInvocationListener;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("La ID no puede ser nula.");
            }
            Product producto = productRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontro el producto con la ID: " + id));
            return ProductMapper.INSTANCE.productToProductDTO(producto);
        } catch (Exception e) {
            throw new EntityNotFoundException("Ha ocurrido un error al obtener el producto con la ID: " + id);
        }
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productoDTO) {
        try {
            if (productoDTO == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo");
            }
            Product productoDB = ProductMapper.INSTANCE.productDTOToProduct(productoDTO);
            productRepository.save(productoDB);
            return ProductMapper.INSTANCE.productToProductDTO(productoDB);

        } catch (Exception e) {
            throw new EntityNotFoundException("Ha ocurrido un error al insertar el producto");
        }

    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO detallesProduct) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("La ID no puede ser nula.");
            }
            if (detallesProduct == null) {
                throw new IllegalArgumentException("Los detalles del producto no pueden ser nulos.");
            }
            if (!productRepository.existsById(id)) {
                throw new EntityNotFoundException("No se encontro el producto con la ID: " + id);
            }

            Product producto = productRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontro el producto con la ID: " + id));
            producto.setName(detallesProduct.name());
            producto.setDescription(detallesProduct.description());
            producto.setPrice(detallesProduct.price());
            producto.setStock(detallesProduct.stock());
            Product productoFromDB = productRepository.save(producto);
            return ProductMapper.INSTANCE.productToProductDTO(productoFromDB);

        } catch (Exception e) {
            throw new RuntimeException("Ha ocurrido un error al actualizar el producto con la ID: " + id);
        }
    }

    @Override
    public void deleteProductById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("La ID no puede ser nula.");
            }
            if (!productRepository.existsById(id)) {
                throw new EntityNotFoundException("No se encontro el producto con la ID: " + id);
            }
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Ha ocurrido un error al eliminar el producto con la ID: " + id);
        }

    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper.INSTANCE::productToProductDTO).collect(Collectors.toList());
    }

}
