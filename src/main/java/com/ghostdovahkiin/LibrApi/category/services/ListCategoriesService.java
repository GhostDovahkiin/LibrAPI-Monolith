package com.ghostdovahkiin.LibrApi.category.services;

import com.ghostdovahkiin.LibrApi.category.Category;

import java.util.List;

@FunctionalInterface
public interface ListCategoriesService {
    List<Category> findAll();
}
