package com.bitnbytes.product.service;
import com.bitnbytes.product.dto.CategoryDTO;
import com.bitnbytes.product.entity.Category;
import com.bitnbytes.product.mapper.CategoryMapper;
import com.bitnbytes.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

private CategoryRepository categoryRepository;


//Create Categories
public CategoryDTO createCategory(CategoryDTO categoryDTO){
    Category category = CategoryMapper.toCategoryEntity(categoryDTO);
    category = categoryRepository.save(category);
    return CategoryMapper.tocategoryDTO(category);
}

//get all categories

    public List<CategoryDTO> getAllCategories(){

       return categoryRepository.findAll().stream()
                .map(CategoryMapper::tocategoryDTO).toList();
    }

    //get CategoryById

    public CategoryDTO getCategoryById(Long id){
    Category category  = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
           return CategoryMapper.tocategoryDTO(category);
    }

    //Delete


    public String deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return "category "+ id + "has been deleted";
    }

}
