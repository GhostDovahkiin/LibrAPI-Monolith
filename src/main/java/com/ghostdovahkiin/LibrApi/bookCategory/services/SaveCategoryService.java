package com.ghostdovahkiin.LibrApi.bookCategory.services;

import com.ghostdovahkiin.LibrApi.bookCategory.bookCategory;

@FunctionalInterface
public interface SaveCategoryService {
    void save(bookCategory bookCategory);
}
