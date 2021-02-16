package com.ghostdovahkiin.librapi.user;

import com.ghostdovahkiin.librapi.exceptions.UserNotFoundException;
import com.ghostdovahkiin.librapi.user.services.DeleteUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Delete User Service")
public class DeleteUserServiceTest {
    @Mock
    private UserRepository userRepository;
    private DeleteUserServiceImpl deleteUserService;

    @BeforeEach
    void setUp() {
        this.deleteUserService = new DeleteUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Should delete a User")
    void shouldDeleteUser() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        deleteUserService.delete(145485989485039832L);
        verify(userRepository).existsById(anyLong());
    }

    @Test
    @DisplayName("Should return a UserNotFoundException if not encountered a user with specified ID")
    void shouldThrowUserNotFoundException() {
        when(userRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> deleteUserService.delete(1L));
        verify(userRepository, times(0)).deleteById(anyLong());
    }


}
