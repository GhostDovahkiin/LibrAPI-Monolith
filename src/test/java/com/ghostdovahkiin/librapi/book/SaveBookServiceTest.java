package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.book.services.SaveBookServiceImpl;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.ghostdovahkiin.librapi.book.builder.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Save Book Service")
class SaveBookServiceTest {
    @Mock
    private BookRepository bookRepository;
    private SaveBookServiceImpl bookService;

    @BeforeEach
    void SetUp() { this.bookService = new SaveBookServiceImpl(bookRepository); }

    @Test
    @DisplayName("Should save a category")
    void shouldSaveCategory() {
        bookService.save(createBook().build());

        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository, times(1)).save(captorBook.capture());

        Book resultBook = captorBook.getValue();

        assertAll("Book",
                () -> assertThat(resultBook.getTitle(), is("book")),
                () -> assertThat(resultBook.getAuthor(), is("author")),
                () -> assertThat(resultBook.getPublicationYear(), is(LocalDate.of(2020, 2, 15))),
                () -> assertThat(resultBook.getAvailableQuantity(), is(2)),
                () -> assertThat(resultBook.getSynopsis(), is("synopsis")),
                () -> assertThat(resultBook.getCategory().size(), is(1)),
                () -> assertThat(resultBook.getSellPrice(), is(15.00))
        );
    }
}
