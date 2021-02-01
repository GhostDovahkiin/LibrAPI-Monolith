package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.User;
import com.ghostdovahkiin.LibrApi.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static com.ghostdovahkiin.LibrApi.user.services.builders.UserBuilder.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Update User Service")
class UpdateUserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UpdateUserServiceImpl updateUserService;

    @BeforeEach
    void setUp() {
        updateUserService = new UpdateUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Should update a user")
    void shouldUpdateUser() {

        User userToUpdate = createUser()
                .name("Name update")
                .age(30)
                .build();

        User user = createUser().build();
        Optional<User> clientOptional  = Optional.of(user);
        when(userRepository.findById(anyLong())).thenReturn(clientOptional);

        updateUserService.update(userToUpdate, 1L);

        ArgumentCaptor<User> clientArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(clientArgumentCaptor.capture());

        User result = clientArgumentCaptor.getValue();

        assertAll("Client",
                () -> assertThat(result.getName(), is(userToUpdate.getName())),
                () -> assertThat(result.getAge(), is(userToUpdate.getAge())),
                () -> assertThat(result.getEmail(), is(userToUpdate.getEmail())),
                () -> assertThat(result.getPhone(), is(userToUpdate.getPhone())),
                () -> assertThat(result.getSex(), is(userToUpdate.getSex()))
        );
    }
}