package com.ghostdovahkiin.librapi.category.services;

import com.ghostdovahkiin.librapi.category.Category;
import com.ghostdovahkiin.librapi.category.CategoryRepository;
import com.ghostdovahkiin.librapi.exceptions.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCategoryServiceImpl implements GetCategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public Category GetById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
}
