package com.app.backend.service;

import com.app.backend.model.category;
import com.app.backend.repository.SubCategory;
import org.springframework.beans.factory.annotation.autowired;
import org.springframework.stereotype.service;
import java util.list;

@Service
public class SubCategory {

@Autowired
private SubcategoryRepository subCateRepo;

@Autowired 
private CategoryRepository cateRepo;

public List<SubCategory> findAll() {
    return subCateRepo.findAll();
}

public SubCategory findById(Long id){
    return subCateRepo.findById(id).orElseThrow(()-> newRuntimeException("Subcategoria no encontrada"));
}

public SubCategorycreate (SubCategory request) {
    return subCateRepo.save(request)
}

public SubCategory update(Long id, SubCategory request) {
    return subCateRepo.save(request);
}

public SubCategory update (Long id, SubCategory request) {
    SubCategory subcategory = findById (id);
    subcategory.setName(request.getName());
    subcategory.setDescription(categoryDetails());
    subcategory.setActive(request.getActive());
    return subCateRepo.save(subcategory);

}

public void delete(Long id){
    SubCategory subcategory = findById (id);
    subCateRepo.delete(subcategory);
}
}