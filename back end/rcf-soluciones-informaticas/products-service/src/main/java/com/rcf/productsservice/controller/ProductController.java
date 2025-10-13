package com.rcf.productsservice.controller;

import com.rcf.productsservice.dto.ProductRequest;
import com.rcf.productsservice.service.ProductService;
import com.rcf.productsservice.storage.ImageStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(@ModelAttribute @Valid ProductRequest productRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
    }
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @ModelAttribute @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(id,productRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("imagen/{imgUrl}")
    public ResponseEntity<?> getImage(@PathVariable String imgUrl) throws MalformedURLException {
        Path filePath = ImageStorageService.root.resolve(imgUrl);
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("No se puede leer la imagen: " + imgUrl);
        }
        String contentType = URLConnection.guessContentTypeFromName(imgUrl);
        if(contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @GetMapping("search")
    public ResponseEntity<?> searchProducts(@RequestParam String name, @RequestParam Long categoryId, @RequestParam Long minPrice, @RequestParam Long maxPrice) {
        return ResponseEntity.ok(productService.searchProducts(name, categoryId, minPrice, maxPrice));
    }
    @GetMapping("search2")
    public ResponseEntity<?> searchProducts2(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchProducts2(name));
    }
}
