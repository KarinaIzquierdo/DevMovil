package com.app.backend.service;

import com.app.backend.model.category;
import com.app.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.service;
import java util.list;

@Service
public class CategoryRepository CategoryRepository;

public List<Category> findAll() {
    return CategoryRepository.findAll();
}

public Category findById(Long id){
    return categoryRepository.findById(id).orElseThrow(()-> newRuntimeException("Categoria no encontrada"));
}
public categorycreate (Category category) {
    return categoryRepository.save(category)
}
public Category update(Long id, Category Category) {
    return categoryRespository.save(category);
}
public Category update (Long id, Category categoryDetails) {
    Category category = findById (id);
    category.setName(categoryDetails.getName());
    category.setDescription(categoryDetails());
    category.setActive(categoryDetails.getActive());
    return categoryRepository.save(category);

}

public void delete(Long id){
    Category category = findById (id);
    categoryRepository.delete(category);
}