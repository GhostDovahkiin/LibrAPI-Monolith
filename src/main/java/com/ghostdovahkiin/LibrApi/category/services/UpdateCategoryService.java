package com.ghostdovahkiin.LibrApi.category.services;

import com.ghostdovahkiin.LibrApi.category.Category;

@FunctionalInterface
public interface UpdateCategoryService {
    void update(Category category, Long id);
}
