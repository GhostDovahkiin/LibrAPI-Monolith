package com.ghostdovahkiin.librapi.category;

import com.ghostdovahkiin.librapi.category.services.GetCategoryServiceImpl;
import com.ghostdovahkiin.librapi.exceptions.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ghostdovahkiin.librapi.category.builder.CategoryBuilder.createCategory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for List One Category Service")
class GetCategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    private GetCategoryServiceImpl categoryService;

    @BeforeEach
    void SetUp(){
        this.categoryService = new GetCategoryServiceImpl(categoryRepository);
    }

    @Test
    @DisplayName("Should return one category")
    void shouldFindOneCategory(){
        Optional<Category> categorySaved = Optional.of(createCategory().build());
        when(categoryRepository.findById(anyLong())).thenReturn(categorySaved);
        Category categoryResult = categoryService.GetById(123L);

        assertAll("Category",
                () -> assertThat(categoryResult.getName(), is("Mathematics"))
        );
        verify(categoryRepository, times(1)).findById(123L);


    }

    @Test
    @DisplayName("Should return a CategoryNotFoundException if not encountered a category with specified ID")
    void shouldThrowCategoryNotFoundException() {
        when(categoryRepository.findById(anyLong())).thenThrow(new CategoryNotFoundException());
        assertThrows(CategoryNotFoundException.class, () -> categoryRepository.findById(1224343L));
        verify(categoryRepository, times(1)).findById(anyLong());
    }
}
