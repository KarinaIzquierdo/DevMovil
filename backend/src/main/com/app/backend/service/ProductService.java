package com.app.backend.service;

import com.app.backend.model.category;
import com.app.backend.repository.ProductService;
import com.app.backend.repository.Category;
import org.springframework.beans.factory.annotation.autowired;
import org.springframework.stereotype.service;
import java util.list;

@Service
public class ProductService {

@Autowired
private ProductRepository proRepo;

@Autowired 
private ProductRepository cateRepo;

public List<Product> findAll() {
    return subCateRepo.findAll();
}

public ProductService findById(Long id){
    return subCateRepo.findById(id).orElseThrow(()-> newRuntimeException("Subcategoria no encontrada"));
}

public List<Subcategoria> findByCategoriaId(Long id) {
    return subCateRepo.findByCategoriaId(id).orElseThrow(() -> new RunTimeException("Categoria no encontrada"))
}

public SubCategorycreate (SubCategory request) {
    return subCateRepo.save(request)
}

public SubCategory update(Long id, SubCategory request) {
    return subCateRepo.save(request);
}

public SubCategory update (Long id, SubCategory request) {
    Product product = findById (id);
    product.setName(request.getName());
    product.setDescription(categoryDetails());
    product.setActive(request.getActive());
    product.setCategory(Product)
    return SubcategoryRepository.save(subcategory);

}

public void delete(Long id){
    SubCategory subcategory = findById (id);
    subCateRepo.delete(subcategory);
}
}