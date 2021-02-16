package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.book.services.UpdateBookServiceImpl;
import com.ghostdovahkiin.librapi.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ghostdovahkiin.librapi.book.builder.BookBuilder.createBook;
import static com.ghostdovahkiin.librapi.category.builder.CategoryBuilder.createCategory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
    void setUp() { this.updateBookService = new UpdateBookServiceImpl(bookRepository); }

    @Test
    @DisplayName("Should update a book")
    void shouldUpdateBook() {

        Book bookToUpdate = createBook()
                .title("Mathematics Fundament")
                .build();

        Optional<Book> bookOptional  = Optional.of(createBook().build());
        when(bookRepository.findById(anyLong())).thenReturn(bookOptional);

        updateBookService.update(bookToUpdate, 123L);

        ArgumentCaptor<Book> clientArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(clientArgumentCaptor.capture());
        Book result = clientArgumentCaptor.getValue();

        assertAll("Book",
                () -> assertThat(result.getTitle(), is("Mathematics Fundament"))
        );
    }
}
