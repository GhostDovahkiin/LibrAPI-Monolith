package com.ghostdovahkiin.librapi.book.builder;

import com.ghostdovahkiin.librapi.book.Book;
import com.ghostdovahkiin.librapi.book.BookDTO;
import com.ghostdovahkiin.librapi.book.BookRepository;
import com.ghostdovahkiin.librapi.book.services.UpdateBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.ghostdovahkiin.librapi.book.builder.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Update Book Service")
class UpdateBookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private UpdateBookServiceImpl updateBookService;

    @BeforeEach
    void SetUp() { this.updateBookService = new UpdateBookServiceImpl(bookRepository); }

    @Test
    @DisplayName("Should update a book")
    void shouldUpdateBook() {
        Book bookToUpdate = createBook().title("miau").build();

        Optional<Book> bookOptional = Optional.of(createBook().build());
        when(bookRepository.findById(anyLong())).thenReturn(bookOptional);
        updateBookService.update(bookToUpdate, 123L);

        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());
        Book bookResult = bookArgumentCaptor.getValue();

        assertAll("Book", () -> assertThat(bookResult.getTitle(), is("miau")),
                () -> assertThat(bookResult.getAuthor(), is("author")),
                () -> assertThat(bookResult.getPublicationYear(), is(LocalDate.of(2020, 2, 15))),
                () -> assertThat(bookResult.getAvailableQuantity(), is(2)),
                () -> assertThat(bookResult.getSynopsis(), is("synopsis")),
                () -> assertThat(bookResult.getSellPrice(), is(15.00)))
        ;
    }
}
