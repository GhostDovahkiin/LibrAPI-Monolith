package com.ghostdovahkiin.librapi.category;

import com.ghostdovahkiin.librapi.category.services.UpdateCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ghostdovahkiin.librapi.category.builder.CategoryBuilder.createCategory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Update Category Service")
class UpdateCategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UpdateCategoryServiceImpl categoryService;

    @BeforeEach()
    void SetUp() {
        this.categoryService = new UpdateCategoryServiceImpl(categoryRepository);
    }

    @Test
    @DisplayName("Should update a category")
    void shouldUpdateCategory() {

        Category categoryToUpdate = createCategory()
                .name("Mathematics")
                .build();

        Optional<Category> categoryOptional  = Optional.of(createCategory().build());
        when(categoryRepository.findById(anyLong())).thenReturn(categoryOptional);

        categoryService.update(categoryToUpdate, 123L);

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        Category result = categoryArgumentCaptor.getValue();

        assertAll("Category",
                () -> assertThat(result.getName(), is("Mathematics"))
        );
    }
}
