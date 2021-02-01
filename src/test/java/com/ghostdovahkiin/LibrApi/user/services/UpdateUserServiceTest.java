package com.ghostdovahkiin.LibrApi.user.services;

import com.ghostdovahkiin.LibrApi.user.Sex;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Update User Service")
public class UpdateUserServiceTest {
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
        when(userRepository.findById(145485989485039832L)).thenReturn(Optional.of(createUser().id(145485989485039832L).build()));

        updateUserService.update(createUser().phone("UPDATED PHONE").name("UPDATED NAME").age(22).email("UPDATED.EMAIL.COM").sex(Sex.MASCULINO).build(), 145485989485039832L);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());

        User resultUser = userCaptor.getValue();

        assertAll("User",
                () -> assertThat(resultUser.getName(), is("UPDATED NAME")),
                () -> assertThat(resultUser.getPhone(), is("UPDATED PHONE")),
                () -> assertThat(resultUser.getAge(), is(22)),
                () -> assertThat(resultUser.getEmail(), is("UPDATED.EMAIL.COM")),
                () -> assertThat(resultUser.getSex(), is(Sex.MASCULINO))
        );
    }
}