package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.book.services.ListBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ghostdovahkiin.librapi.book.builder.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for List All Books Service")
class ListBookServiceTest {
    @Mock
    private BookRepository bookRepository;
    private ListBookServiceImpl bookService;

    @BeforeEach
    void setUp() { this.bookService = new ListBookServiceImpl(bookRepository); }

    @Test
    @DisplayName("Should return all books")
    void shouldFindAllBooks() {
        when(bookRepository.findAll()).thenReturn(
                Stream.of(createBook().title("book1").build(),
                        createBook().title("book2").build(),
                        createBook().title("book3").build()).collect(Collectors.toList())
        );

        List<Book> booksFound = bookService.findAll();

        assertAll("Books",
                () -> assertThat(booksFound.size(), is(3)),
                () -> assertThat(booksFound.get(0).getTitle(), is("book1")),
                () -> assertThat(booksFound.get(0).getAuthor(), is("author")),
                () -> assertThat(booksFound.get(0).getPublicationYear(), is(LocalDate.of(2020, 2, 15))),
                () -> assertThat(booksFound.get(0).getAvailableQuantity(), is(2)),
                () -> assertThat(booksFound.get(0).getSynopsis(), is("synopsis")),
                () -> assertThat(booksFound.get(0).getSellPrice(), is(15.00)),
                () -> assertThat(booksFound.get(1).getTitle(), is("book2")),
                () -> assertThat(booksFound.get(2).getTitle(), is("book3"))
        );

        verify(bookRepository, times(1)).findAll();
    }
}
