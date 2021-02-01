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

import static com.ghostdovahkiin.LibrApi.user.services.builders.UserBuilder.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Save User Service")
class SaveUserServiceTest {

    @Mock
    private UserRepository userRepository;
    private SaveUserServiceImpl saveUserService;

    @BeforeEach
    void setUp() {
        this.saveUserService = new SaveUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Should save a user")
    void shoulSaveUser() {
        saveUserService.save(createUser().build());

        ArgumentCaptor<User> captorUser = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(captorUser.capture());

        User resultUser = captorUser.getValue();

        assertAll("User",
                () -> assertThat(resultUser.getName(), is("Pedro")),
                () -> assertThat(resultUser.getAge(), is(22)),
                () -> assertThat(resultUser.getPhone(), is("+5583986862912"))
        );
    }
}