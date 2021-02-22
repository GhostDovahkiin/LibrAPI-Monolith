package com.ghostdovahkiin.librapi.user;

import com.ghostdovahkiin.librapi.user.services.UpdateUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ghostdovahkiin.librapi.user.builders.UserBuilder.createUser;
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

        Optional<User> clientOptional  = Optional.of(createUser().build());
        when(userRepository.findById(anyLong())).thenReturn(clientOptional);

        updateUserService.update(UserDTO.from(userToUpdate), 1L);

        ArgumentCaptor<User> clientArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(clientArgumentCaptor.capture());

        User result = clientArgumentCaptor.getValue();

        assertAll("Client",
                () -> assertThat(result.getName(), is("Name update")),
                () -> assertThat(result.getAge(), is(30)),
                () -> assertThat(result.getEmail(), is("pedro.sousa@dcx.ufpb.br")),
                () -> assertThat(result.getPhone(), is("+5583986862912")),
                () -> assertThat(result.getSex(), is(Sex.MALE))
        );
    }
}