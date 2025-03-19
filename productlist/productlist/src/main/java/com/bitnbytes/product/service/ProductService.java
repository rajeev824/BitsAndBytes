package com.bitnbytes.product.service;

import com.bitnbytes.product.dto.ProductDTO;
import com.bitnbytes.product.entity.Category;
import com.bitnbytes.product.entity.Product;
import com.bitnbytes.product.mapper.ProductMapper;
import com.bitnbytes.product.repository.CategoryRepository;
import com.bitnbytes.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Create Product
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Fetch category by ID
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Convert DTO to entity
        Product product = ProductMapper.toProductEntity(productDTO, category);

        // Save entity to DB
        product = productRepository.save(product);

        // Convert entity back to DTO and return
        return ProductMapper.toProductDTO(product);
    }

    // Get all products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toProductDTO)
                .toList();
    }

    // Get product by ID
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toProductDTO(product);
    }

    // Update Product
    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        // Fetch existing product by ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Fetch category by ID
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Updating the product entity
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        // Save the updated product
        product = productRepository.save(product);

        // Convert and return the updated product as a DTO
        return ProductMapper.toProductDTO(product);
    }

    // Delete Product
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product " + id + " has been deleted.";
    }
}
