package com.alessandropaiano.gamingeshop.controller;

import com.alessandropaiano.gamingeshop.dto.ProductDTO;
import com.alessandropaiano.gamingeshop.model.Product;
import com.alessandropaiano.gamingeshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        Optional<Product> product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product createdProduct = productService.saveProduct(product);

        ProductDTO createdProductDTO = modelMapper.map(createdProduct, ProductDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productDTO.setId(id); // Assumendo che l'ID non possa essere modificato
        Product product = modelMapper.map(productDTO, Product.class);
        Product updatedProduct = productService.updateProduct(product);

        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }

        ProductDTO updatedProductDTO = modelMapper.map(updatedProduct, ProductDTO.class);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
