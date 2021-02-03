package com.ghostdovahkiin.LibrApi.category.services;

import com.ghostdovahkiin.LibrApi.category.Category;

@FunctionalInterface
public interface GetCategoryService {
    Category GetById(Long id);
}
