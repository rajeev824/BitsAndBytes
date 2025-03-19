package com.bitnbytes.product.service;
import com.bitnbytes.product.dto.CategoryDTO;
import com.bitnbytes.product.entity.Category;
import com.bitnbytes.product.mapper.CategoryMapper;
import com.bitnbytes.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

private CategoryRepository categoryRepository;


public CategoryDTO createCategory(CategoryDTO categoryDTO){
    Category category = CategoryMapper.toCategoryEntity(categoryDTO);
    category = categoryRepository.save(category);
    return CategoryMapper.tocategoryDTO(category);
}


}
