package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.book.services.ListPageBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;

import static com.ghostdovahkiin.librapi.book.builder.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for List Page Book Service")
class ListPageBookServiceTest {
    @Mock
    private BookRepository bookRepository;
    private ListPageBookServiceImpl bookService;

    @BeforeEach
    void setUp() { this.bookService = new ListPageBookServiceImpl(bookRepository); }

    @Test
    @DisplayName("Should return all books with pagination")
    void shouldReturnBooksAsAPage() {
        when(bookService.listBooks(PageRequest.of(0, 2)))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, createBook().build())));

        Page<Book> booksPageable = bookService.listBooks(PageRequest.of(0,2));

        assertAll("Books",
                () -> assertThat(booksPageable.getNumber(), is(0)),
                () -> assertThat(booksPageable.getSize(), is(2)),
                () -> assertThat(booksPageable.getContent().get(0).getTitle(), is("book")),
                () -> assertThat(booksPageable.getContent().get(0).getAuthor(), is("author")),
                () -> assertThat(booksPageable.getContent().get(0).getPublicationYear(), is(LocalDate.of(2020, 2, 15))),
                () -> assertThat(booksPageable.getContent().get(0).getAvailableQuantity(), is(2)),
                () -> assertThat(booksPageable.getContent().get(0).getSynopsis(), is("synopsis")),
                () -> assertThat(booksPageable.getContent().get(0).getSellPrice(), is(15.00))
        );


    }
}
