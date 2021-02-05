package com.ghostdovahkiin.LibrApi.category.services;

import com.ghostdovahkiin.LibrApi.category.Category;
import com.ghostdovahkiin.LibrApi.category.CategoryRepository;
import com.ghostdovahkiin.LibrApi.exceptions.CategoryNotFoundException;
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
