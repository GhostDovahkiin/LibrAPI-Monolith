package com.ghostdovahkiin.LibrApi.category.v1;

import com.ghostdovahkiin.LibrApi.category.Category;
import com.ghostdovahkiin.LibrApi.category.CategoryDTO;
import com.ghostdovahkiin.LibrApi.category.services.DeleteCategoryService;
import com.ghostdovahkiin.LibrApi.category.services.GetCategoryService;
import com.ghostdovahkiin.LibrApi.category.services.ListCategoriesService;
import com.ghostdovahkiin.LibrApi.category.services.SaveCategoryService;
import com.ghostdovahkiin.LibrApi.category.services.UpdateCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/api/categories")
public class CategoryController {

    private final GetCategoryService getCategoryService;
    private final ListCategoriesService listCategoriesService;
    private final SaveCategoryService saveCategoryService;
    private final UpdateCategoryService updateCategoryService;
    private final DeleteCategoryService deleteCategoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> findAll() {
        return CategoryDTO.fromAll(listCategoriesService.findAll());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO findById(@PathVariable Long id) {
        return CategoryDTO.from(getCategoryService.GetById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody CategoryDTO categoryDTO) {
        saveCategoryService.save(Category.to(categoryDTO));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        updateCategoryService.update(Category.to(categoryDTO), id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteCategoryService.delete(id);
    }

}
