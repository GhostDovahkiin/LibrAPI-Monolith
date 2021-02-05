package com.ghostdovahkiin.LibrApi.category;

import com.ghostdovahkiin.LibrApi.category.services.ListCategoriesServiceImpl;
import com.ghostdovahkiin.LibrApi.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ghostdovahkiin.LibrApi.category.builder.CategoryBuilder.createCategory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static com.ghostdovahkiin.LibrApi.user.builders.UserBuilder.createUser;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for List All Categories Service")
public class ListCategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    private ListCategoriesServiceImpl categoriesService;

    @BeforeEach
    void SetUp() {
        this.categoriesService = new ListCategoriesServiceImpl(categoryRepository);
    }

    @Test
    @DisplayName("Should return all categories")
    void shouldFindAllCategories() {
        when(categoryRepository.findAll()).thenReturn(
                Stream.of(createCategory().name("Português").build(),
                        createCategory().name("Geografia").build(),
                        createCategory().name("Matemática").build()).collect(Collectors.toList())
        );

        List<Category> categoriesFound = categoriesService.findAll();

        assertAll("Users",
                () -> assertThat(categoriesFound.size(), is(3)),
                () -> assertThat(categoriesFound.get(0).getName(), is("Português")),
                () -> assertThat(categoriesFound.get(1).getName(), is("Geografia")),
                () -> assertThat(categoriesFound.get(2).getName(), is("Matemática"))
        );

        verify(categoryRepository, times(1)).findAll();
    }
}
