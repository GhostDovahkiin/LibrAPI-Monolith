package com.ghostdovahkiin.librapi.book;

import com.ghostdovahkiin.librapi.book.services.DeleteBookServiceImpl;
import com.ghostdovahkiin.librapi.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Delete Book Service")
class DeleteBookServiceTest {
    @Mock
    private BookRepository bookRepository;
    private DeleteBookServiceImpl deleteBookService;

    @BeforeEach
    void SetUp() { this.deleteBookService = new DeleteBookServiceImpl(bookRepository); }

    @Test
    @DisplayName("Should delete a Book")
    void shouldDeleteUser() {
        when(bookRepository.existsById(anyLong())).thenReturn(true);
        deleteBookService.delete(123L);
        verify(bookRepository).existsById(anyLong());
    }

    @Test
    @DisplayName("Should return a BookNotFoundException if not encountered a Book with specified ID")
    void shouldThrowUserNotFoundException() {
        when(bookRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(BookNotFoundException.class, () -> deleteBookService.delete(1L));
        verify(bookRepository, times(0)).deleteById(anyLong());
    }
}
