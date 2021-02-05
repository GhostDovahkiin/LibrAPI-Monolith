package com.ghostdovahkiin.LibrApi.category.services;

import com.ghostdovahkiin.LibrApi.category.Category;
import com.ghostdovahkiin.LibrApi.category.CategoryRepository;
import com.ghostdovahkiin.LibrApi.exceptions.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateCategoryServiceImpl implements UpdateCategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public void update(Category category, Long id) {
        Category categoryFound = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryFound.setName(category.getName());
        categoryRepository.save(categoryFound);
    }
}
