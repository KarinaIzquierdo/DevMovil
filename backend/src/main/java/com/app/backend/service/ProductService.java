package com.app.backend.service;

import com.app.backend.model.Product;
import com.app.backend.repository.ProductRepository;
import com.app.backend.repository.SubcategoryRepository;
import com.app.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> findBySubcategoryId(Long subcategoryId) {
        return productRepository.findBySubcategoryId(subcategoryId);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Product create(Product product) {
        // Validar y cargar la categoría
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            var category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategory(category);
        } else {
            throw new RuntimeException("La categoría es requerida");
        }

        // Validar y cargar la subcategoría
        if (product.getSubcategory() != null && product.getSubcategory().getId() != null) {
            var subcategory = subcategoryRepository.findById(product.getSubcategory().getId())
                    .orElseThrow(() -> new RuntimeException("Subcategoría no encontrada"));
            product.setSubcategory(subcategory);
        } else {
            throw new RuntimeException("La subcategoría es requerida");
        }

        return productRepository.save(product);
    }

    public Product update(Long id, Product productDetails) {
        Product product = findById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setActive(productDetails.getActive());
        
        // Validar y cargar la categoría si viene
        if (productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
            var category = categoryRepository.findById(productDetails.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategory(category);
        }

        // Validar y cargar la subcategoría si viene
        if (productDetails.getSubcategory() != null && productDetails.getSubcategory().getId() != null) {
            var subcategory = subcategoryRepository.findById(productDetails.getSubcategory().getId())
                    .orElseThrow(() -> new RuntimeException("Subcategoría no encontrada"));
            product.setSubcategory(subcategory);
        }
        
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}