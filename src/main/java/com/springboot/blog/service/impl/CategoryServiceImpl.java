package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;


    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDto.class);
    }
}
