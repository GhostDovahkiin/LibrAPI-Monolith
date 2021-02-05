package com.ghostdovahkiin.LibrApi.category.builder;

import com.ghostdovahkiin.LibrApi.category.Category;

public class CategoryBuilder {
    public static Category.Builder createCategory() {
        return Category.builder()
                .id(123L)
                .name("Mathematics");
    }
}
