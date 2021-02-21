package com.ghostdovahkiin.librapi.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostdovahkiin.librapi.book.services.DeleteBookService;
import com.ghostdovahkiin.librapi.book.services.GetBookService;
import com.ghostdovahkiin.librapi.book.services.ListBookByCategoryNameService;
import com.ghostdovahkiin.librapi.book.services.ListBookService;
import com.ghostdovahkiin.librapi.book.services.ListPageBookService;
import com.ghostdovahkiin.librapi.book.services.SaveBookService;
import com.ghostdovahkiin.librapi.book.services.UpdateBookService;
import com.ghostdovahkiin.librapi.book.v1.BookController;
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

import static com.ghostdovahkiin.librapi.book.builder.BookBuilder.createBook;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
@WebMvcTest(controllers = BookController.class)
@DisplayName("Tests all implemented tests from services if working on controller")
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeleteBookService deleteBookService;

    @MockBean
    private GetBookService getBookService;

    @MockBean
    private ListBookByCategoryNameService listBookByCategoryNameService;

    @MockBean
    private ListBookService listBookService;

    @MockBean
    private SaveBookService saveBookService;

    @MockBean
    private ListPageBookService listPageBookService;

    @MockBean
    private UpdateBookService updateBookService;

    private final String URL = "/v1/api/books";

    @Test
    @DisplayName("Should find and return all books")
    void shouldFindAllBooks() throws Exception{
        when(listBookService.findAll()).thenReturn(
                Lists.newArrayList(
                        createBook().id(1234L).title("teste1").build(),
                        createBook().id(2468L).title("teste2").build(),
                        createBook().id(1357L).title("teste3").build()
                ));

        mockMvc.perform(get(URL + "/all").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1234)))
                .andExpect(jsonPath("$[0].isbn", is("12345678912345678")))
                .andExpect(jsonPath("$[0].title", is("teste1")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].publicationYear", is("2020-02-15")))
                .andExpect(jsonPath("$[0].availableQuantity", is(2)))
                .andExpect(jsonPath("$[0].synopsis", is("synopsis")))
                .andExpect(jsonPath("$[0].category.[0].id", is(1)))
                .andExpect(jsonPath("$[0].category.[0].name", is("Geografia")))
                .andExpect(jsonPath("$[1].id", is(2468)))
                .andExpect(jsonPath("$[1].title", is("teste2")))
                .andExpect(jsonPath("$[1].isbn", is("12345678912345678")))
                .andExpect(jsonPath("$[1].author", is("author")))
                .andExpect(jsonPath("$[1].publicationYear", is("2020-02-15")))
                .andExpect(jsonPath("$[1].availableQuantity", is(2)))
                .andExpect(jsonPath("$[1].synopsis", is("synopsis")))
                .andExpect(jsonPath("$[1].category.[0].id", is(1)))
                .andExpect(jsonPath("$[1].category.[0].name", is("Geografia")))
                .andExpect(jsonPath("$[2].id", is(1357)))
                .andExpect(jsonPath("$[2].title", is("teste3")))
                .andExpect(jsonPath("$[2].isbn", is("12345678912345678")))
                .andExpect(jsonPath("$[2].author", is("author")))
                .andExpect(jsonPath("$[2].publicationYear", is("2020-02-15")))
                .andExpect(jsonPath("$[2].availableQuantity", is(2)))
                .andExpect(jsonPath("$[2].synopsis", is("synopsis")))
                .andExpect(jsonPath("$[2].category.[0].id", is(1)))
                .andExpect(jsonPath("$[2].category.[0].name", is("Geografia"))
        );
        verify(listBookService).findAll();
    }

    @Test
    @DisplayName("Should find and return one book")
    void shouldFindOneBook() throws Exception{
        when(getBookService.findById(anyLong())).thenReturn(createBook().id(1234L).title("teste1").build());

        mockMvc.perform(get(URL + "/{id}", 123L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1234)))
                .andExpect(jsonPath("$.isbn", is("12345678912345678")))
                .andExpect(jsonPath("$.title", is("teste1")))
                .andExpect(jsonPath("$.author", is("author")))
                .andExpect(jsonPath("$.publicationYear", is("2020-02-15")))
                .andExpect(jsonPath("$.availableQuantity", is(2)))
                .andExpect(jsonPath("$.synopsis", is("synopsis")))
                .andExpect(jsonPath("$.category.[0].id", is(1)))
                .andExpect(jsonPath("$.category.[0].name", is("Geografia"))
        );
        verify(getBookService).findById(anyLong());
    }

    @Test
    @DisplayName("Should find and all books by category name")
    void shouldFindBookByCategoryName() throws Exception{
        when(listBookByCategoryNameService.findBookByCategoriesName(anyString())).thenReturn(Lists.newArrayList(
                createBook().id(1234L).title("teste1").build(),
                createBook().id(2468L).title("teste2").build(),
                createBook().id(1357L).title("teste3").build()
        ));

        mockMvc.perform(get(URL + "/category/{categoryName}", "Geografia").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1234)))
                .andExpect(jsonPath("$[0].isbn", is("12345678912345678")))
                .andExpect(jsonPath("$[0].title", is("teste1")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].publicationYear", is("2020-02-15")))
                .andExpect(jsonPath("$[0].availableQuantity", is(2)))
                .andExpect(jsonPath("$[0].synopsis", is("synopsis")))
                .andExpect(jsonPath("$[0].category.[0].id", is(1)))
                .andExpect(jsonPath("$[0].category.[0].name", is("Geografia")))
                .andExpect(jsonPath("$[1].id", is(2468)))
                .andExpect(jsonPath("$[1].title", is("teste2")))
                .andExpect(jsonPath("$[1].isbn", is("12345678912345678")))
                .andExpect(jsonPath("$[1].author", is("author")))
                .andExpect(jsonPath("$[1].publicationYear", is("2020-02-15")))
                .andExpect(jsonPath("$[1].availableQuantity", is(2)))
                .andExpect(jsonPath("$[1].synopsis", is("synopsis")))
                .andExpect(jsonPath("$[1].category.[0].id", is(1)))
                .andExpect(jsonPath("$[1].category.[0].name", is("Geografia")))
                .andExpect(jsonPath("$[2].id", is(1357)))
                .andExpect(jsonPath("$[2].title", is("teste3")))
                .andExpect(jsonPath("$[2].isbn", is("12345678912345678")))
                .andExpect(jsonPath("$[2].author", is("author")))
                .andExpect(jsonPath("$[2].publicationYear", is("2020-02-15")))
                .andExpect(jsonPath("$[2].availableQuantity", is(2)))
                .andExpect(jsonPath("$[2].synopsis", is("synopsis")))
                .andExpect(jsonPath("$[2].category.[0].id", is(1)))
                .andExpect(jsonPath("$[2].category.[0].name", is("Geografia"))
                );
        verify(listBookByCategoryNameService).findBookByCategoriesName(anyString());
    }

    @Test
    @DisplayName("Should save an Book")
    void shouldSaveBook() throws Exception {
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(readJson("BookDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated()
        );
        verify(saveBookService, times(1)).save(any(Book.class));
    }

    @Test
    @DisplayName("Should update an Book")
    void shouldUpdateBook() throws Exception {
        mockMvc.perform(put(URL + "/{id}", 123L)
                .content(readJson("BookDTOUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(updateBookService).update(any(Book.class), eq(123L));
    }

    @Test
    @DisplayName("Should delete an Book")
    void shouldRemoveBook() throws Exception {
        mockMvc.perform(delete(URL + "/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(deleteBookService).delete(anyLong());
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
