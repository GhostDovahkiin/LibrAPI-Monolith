package com.ghostdovahkiin.librapi.user;

import com.ghostdovahkiin.librapi.exceptions.UserNotFoundException;
import com.ghostdovahkiin.librapi.user.services.GetUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ghostdovahkiin.librapi.user.builders.UserBuilder.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for List One User Service")
class GetUserServiceTest {

    @Mock
    private UserRepository userRepository;
    private GetUserServiceImpl getOneUserService;

    @BeforeEach
    void setUp() { this.getOneUserService = new GetUserServiceImpl(userRepository); }

    @Test
    @DisplayName("Should return one user")
    void shouldFindOneUser(){
        Optional<User> userSaved = Optional.of(createUser().build());
        when(userRepository.findById(anyLong())).thenReturn(userSaved);
        User userResult = getOneUserService.findById(145485989485039832L);

        assertAll("User",
                () -> assertThat(userResult.getName(), is("Pedro")),
                () -> assertThat(userResult.getAge(), is(22)),
                () -> assertThat(userResult.getEmail(), is("pedro.sousa@dcx.ufpb.br")),
                () -> assertThat(userResult.getPhone(), is("+5583986862912")),
                () -> assertThat(userResult.getSex(), is(Sex.MALE))
        );
        verify(userRepository, times(1)).findById(145485989485039832L);


    }

    @Test
    @DisplayName("Should return a UserNotFoundException if not encountered a user with specified ID")
    void shouldThrowUserNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> getOneUserService.findById(4993L));
        verify(userRepository, times(1)).findById(anyLong());
    }
}
