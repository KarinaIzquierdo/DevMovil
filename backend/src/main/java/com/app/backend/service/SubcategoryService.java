package com.app.backend.service;

import com.app.backend.model.Subcategory;
import com.app.backend.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryService categoryService;

    public SubcategoryService(SubcategoryRepository subcategoryRepository, CategoryService categoryService) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryService = categoryService;
    }

    public List<Subcategory> findAll() {
        return subcategoryRepository.findAll();
    }

    public List<Subcategory> findByCategoryId(Long categoryId) {
        return subcategoryRepository.findByCategoryId(categoryId);
    }

    public Subcategory findById(Long id) {
        return subcategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Subcategoria no encontrada"));
    }

    public Subcategory create(Subcategory subcategory) {
        if (subcategory.getCategory() != null && subcategory.getCategory().getId() != null) {
            subcategory.setCategory(categoryService.findById(subcategory.getCategory().getId()));
        }
        return subcategoryRepository.save(subcategory);
    }

    public Subcategory update(Long id, Subcategory subcategoryDetails) {
        Subcategory subcategory = findById(id);
        subcategory.setName(subcategoryDetails.getName());
        subcategory.setDescription(subcategoryDetails.getDescription());
        subcategory.setActive(subcategoryDetails.getActive());
        return subcategoryRepository.save(subcategory);
    }

    public void delete(Long id) {
        Subcategory subcategory = findById(id);
        subcategoryRepository.delete(subcategory);
    }
}