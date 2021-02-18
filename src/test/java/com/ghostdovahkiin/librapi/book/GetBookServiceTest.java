package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.book.services.GetBookServiceImpl;
import com.ghostdovahkiin.librapi.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.ghostdovahkiin.librapi.book.builder.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for List One Book Service")
class GetBookServiceTest {
    @Mock
    private BookRepository bookRepository;
    private GetBookServiceImpl bookService;

    @BeforeEach
    void SetUp() { this.bookService = new GetBookServiceImpl(bookRepository);}

    @Test
    @DisplayName("Should return one book")
    void shouldFindOneBook() {
        Optional<Book> bookSaved = Optional.of(createBook().build());
        when(bookRepository.findById(anyLong())).thenReturn(bookSaved);
        Book bookResult = bookService.findById(123L);
        assertAll("Book", () -> assertThat(bookResult.getIsbn(), is("12345678912345678")),
                () -> assertThat(bookResult.getTitle(), is("book")),
                () -> assertThat(bookResult.getAuthor(), is("author")),
                () -> assertThat(bookResult.getPublicationYear(), is(LocalDate.of(2020, 2, 15))),
                () -> assertThat(bookResult.getAvailableQuantity(), is(2)),
                () -> assertThat(bookResult.getSynopsis(), is("synopsis")),
                () -> assertThat(bookResult.getSellPrice(), is(15.00))
        );
        verify(bookRepository, times(1)).findById(123L);
    }

    @Test
    @DisplayName("Should return a BookNotFoundException if not encountered a book with specified ID")
    void shouldThrowBookNotFoundException() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.findById(1224343333L));
        verify(bookRepository, times(1)).findById(anyLong());
    }
}
