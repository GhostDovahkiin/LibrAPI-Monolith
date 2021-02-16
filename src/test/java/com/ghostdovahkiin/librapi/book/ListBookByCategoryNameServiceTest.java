package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.book.services.ListBookByCategoryNameServiceImpl;
import com.ghostdovahkiin.librapi.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ghostdovahkiin.librapi.book.builder.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for ListBookByCategoryNameService")
class ListBookByCategoryNameServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ListBookByCategoryNameServiceImpl bookByCategoryNameService;

    @BeforeEach()
    void SetUp() {
        this.bookByCategoryNameService = new ListBookByCategoryNameServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Should return all books with Geography category")
    void shouldFindAllGeographyBooks() {
        when(bookRepository.findBookByCategoryName(anyString())).thenReturn(Stream.of(createBook().title("book1").build(),
                createBook().title("book2").build(),
                createBook().title("book3").build()).collect(Collectors.toList())
        );

        List<Book> booksFound = bookByCategoryNameService.findBookByCategoriesName("Geografia");

        assertAll("Books",
                () -> assertThat(booksFound.size(), is(3)),
                () -> assertThat(booksFound.get(0).getTitle(), is("book1")),
                () -> assertThat(booksFound.get(0).getCategory().toArray().length, is(1)),
                () -> assertThat(booksFound.get(0).getAuthor(), is("author")),
                () -> assertThat(booksFound.get(0).getPublicationYear(), is(LocalDate.of(2020, 2, 15))),
                () -> assertThat(booksFound.get(0).getAvailableQuantity(), is(2)),
                () -> assertThat(booksFound.get(0).getSynopsis(), is("synopsis")),
                () -> assertThat(booksFound.get(0).getSellPrice(), is(15.00))
        );
        verify(bookRepository, times(1)).findBookByCategoryName(anyString());
    }
}
