package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    }
}
