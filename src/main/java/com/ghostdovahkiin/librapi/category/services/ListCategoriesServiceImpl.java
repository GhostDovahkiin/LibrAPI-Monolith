package com.ghostdovahkiin.librapi.category.services;

import com.ghostdovahkiin.librapi.category.Category;
import com.ghostdovahkiin.librapi.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListCategoriesServiceImpl implements ListCategoriesService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
