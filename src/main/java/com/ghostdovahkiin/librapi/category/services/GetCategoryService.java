package com.ghostdovahkiin.librapi.category.services;

import com.ghostdovahkiin.librapi.category.Category;

@FunctionalInterface
public interface GetCategoryService {
    Category GetById(Long id);
}
