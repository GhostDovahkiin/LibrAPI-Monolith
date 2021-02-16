package com.ghostdovahkiin.librapi.category.services;

import com.ghostdovahkiin.librapi.category.Category;
import com.ghostdovahkiin.librapi.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveCategoryServiceImpl implements SaveCategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public void save(Category bookCategory) {
        categoryRepository.save(bookCategory);
    }
}
