package com.bitnbytes.product.controller;

import com.bitnbytes.product.dto.ProductDTO;
import com.bitnbytes.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

//GetAllProduct
    @GetMapping
    public List<ProductDTO>getAllProduct(){
        return productService.getAllProducts();
    }

    //Get Product By Id

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
       return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity <ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

//Update Product
@PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
    return productService.updateProduct(id, productDTO);
}



//    @PutMapping("/{id}")
//    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
//        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
//        return ResponseEntity.ok(updatedProduct);
//    }




//delete by id
 @DeleteMapping("/{id}")
 public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
 }


}
