package com.ghostdovahkiin.librapi.category.services;

import com.ghostdovahkiin.librapi.category.Category;

import java.util.List;

@FunctionalInterface
public interface ListCategoriesService {
    List<Category> findAll();
}
