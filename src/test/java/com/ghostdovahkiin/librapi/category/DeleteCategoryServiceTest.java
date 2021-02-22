package com.ghostdovahkiin.librapi.category;

import com.ghostdovahkiin.librapi.category.services.DeleteCategoryServiceImpl;
import com.ghostdovahkiin.librapi.exceptions.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Delete Category Service")
class DeleteCategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    private DeleteCategoryServiceImpl deleteCategoryService;

    @BeforeEach
    void SetUp(){
        this.deleteCategoryService = new DeleteCategoryServiceImpl(categoryRepository);
    }

    @Test
    @DisplayName("Should delete a Category")
    void shouldDeleteCategory() {
        when(categoryRepository.existsById(anyLong())).thenReturn(true);
        deleteCategoryService.delete(123L);
        verify(categoryRepository).existsById(anyLong());
    }

    @Test
    @DisplayName("Should return a CategoryNotFoundException if not encountered a categorry with specified ID")
    void shouldThrowCategoryNotFoundException() {
        when(categoryRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(CategoryNotFoundException.class, () -> deleteCategoryService.delete(1L));
        verify(categoryRepository, times(0)).deleteById(anyLong());
    }
}
