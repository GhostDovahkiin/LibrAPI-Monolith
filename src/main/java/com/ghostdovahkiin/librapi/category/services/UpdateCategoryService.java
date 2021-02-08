package com.ghostdovahkiin.librapi.category.services;

import com.ghostdovahkiin.librapi.category.Category;

@FunctionalInterface
public interface UpdateCategoryService {
    void update(Category category, Long id);
}
