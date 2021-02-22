package com.ghostdovahkiin.librapi.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostdovahkiin.librapi.category.services.DeleteCategoryService;
import com.ghostdovahkiin.librapi.category.services.GetCategoryService;
import com.ghostdovahkiin.librapi.category.services.ListCategoriesService;
import com.ghostdovahkiin.librapi.category.services.SaveCategoryService;
import com.ghostdovahkiin.librapi.category.services.UpdateCategoryService;
import com.ghostdovahkiin.librapi.category.v1.CategoryController;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.ghostdovahkiin.librapi.category.builder.CategoryBuilder.createCategory;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryController.class)
@DisplayName("Tests all implemented tests from services if working on controller")
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeleteCategoryService deleteCategoryService;
    @MockBean
    private GetCategoryService getCategoryService;
    @MockBean
    private ListCategoriesService listCategoriesService;
    @MockBean
    private SaveCategoryService saveCategoryService;
    @MockBean
    private UpdateCategoryService updateCategoryService;

    private final String URLREQ = "/v1/api/categories";

    @Test
    @DisplayName("Should find and return all categories")
    void shouldFindAllCategories() throws Exception{
        when(listCategoriesService.findAll()).thenReturn(
                Lists.newArrayList(
                        createCategory().id(1234L).name("teste1").build(),
                        createCategory().id(2468L).name("teste2").build(),
                        createCategory().id(1357L).name("teste3").build()
                ));

        mockMvc.perform(get(URLREQ).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1234)))
                .andExpect(jsonPath("$[0].name", is("teste1")))
                .andExpect(jsonPath("$[1].id", is(2468)))
                .andExpect(jsonPath("$[1].name", is("teste2")))
                .andExpect(jsonPath("$[2].id", is(1357)))
                .andExpect(jsonPath("$[2].name", is("teste3"))
        );
        verify(listCategoriesService).findAll();
    }

    @Test
    @DisplayName("Should find and return one category")
    void shouldFindCategoryById() throws Exception{
        when(getCategoryService.GetById(1234L))
                .thenReturn(createCategory()
                        .id(1234L)
                        .name("teste1")
                        .build());

        mockMvc.perform(get(URLREQ + "/{id}", 1234L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1234)))
                .andExpect(jsonPath("$.name", is("teste1"))
        );
        verify(getCategoryService, times(1)).GetById(anyLong());

    }

    @Test
    @DisplayName("Should save an Category")
    void shouldSaveCategory() throws Exception {
        mockMvc.perform(post(URLREQ).contentType(MediaType.APPLICATION_JSON)
                .content(readJson("CategoryDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated()
                );

        verify(saveCategoryService, times(1)).save(any(Category.class));
    }

    @Test
    @DisplayName("Should update an Category")
    void shouldUpdateCategory() throws Exception {
        mockMvc.perform(put(URLREQ + "/{id}", 123L)
                .content(readJson("CategoryDTOUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(updateCategoryService).update(any(Category.class), eq(123L));
    }

    @Test
    @DisplayName("Should delete an Category")
    void shouldRemoveCategory() throws Exception {
        mockMvc.perform(delete(URLREQ + "/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteCategoryService).delete(anyLong());
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
