package com.bitnbytes.product.mapper;

import com.bitnbytes.product.dto.ProductDTO;
import com.bitnbytes.product.entity.Category;
import com.bitnbytes.product.entity.Product;

public class ProductMapper {

    // ✅ Entity to DTO Conversion
    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId()  // ✅ Ensure Category is not null before calling getId()
        );
    }

    // ✅ DTO to Entity Conversion
    public static Product toProductEntity(ProductDTO productDTO, Category category) {
        Product product = new Product();
        //product.setId(productDTO.getId()); // ✅ If ProductDTO has an ID, set it
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice()); // ✅ Fix: `productDTO.getPrice()` (not `ProductDTO.getPrice()`)
        product.setCategory(category);
        return product;
    }
}
