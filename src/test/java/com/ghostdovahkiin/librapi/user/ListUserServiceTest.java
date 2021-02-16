package com.ghostdovahkiin.librapi.user;

import com.ghostdovahkiin.librapi.user.services.ListUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static com.ghostdovahkiin.librapi.user.builders.UserBuilder.createUser;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for List All Users Service")
class ListUserServiceTest {

    @Mock
    private UserRepository userRepository;
    private ListUserServiceImpl listAllUserService;

    @BeforeEach
    void setUp() {
        this.listAllUserService = new ListUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Should return all users")
    void shouldFindAllUsers() {
        when(userRepository.findAll()).thenReturn(
                Stream.of(createUser().name("Pedro 1").build(),
                          createUser().name("Pedro 2").build(),
                          createUser().name("Pedro 3").build()).collect(Collectors.toList())
        );

        List<User> usersFound = listAllUserService.findAll();

        assertAll("Users",
                () -> assertThat(usersFound.size(), is(3)),
                () -> assertThat(usersFound.get(0).getName(), is("Pedro 1")),
                () -> assertThat(usersFound.get(0).getSex(), is(Sex.MASCULINO)),
                () -> assertThat(usersFound.get(0).getEmail(), is("pedro.sousa@dcx.ufpb.br")),
                () -> assertThat(usersFound.get(0).getPhone(), is("+5583986862912")),
                () -> assertThat(usersFound.get(0).getAge(), is(22)),
                () -> assertThat(usersFound.get(1).getName(), is("Pedro 2")),
                () -> assertThat(usersFound.get(1).getSex(), is(Sex.MASCULINO)),
                () -> assertThat(usersFound.get(1).getEmail(), is("pedro.sousa@dcx.ufpb.br")),
                () -> assertThat(usersFound.get(1).getPhone(), is("+5583986862912")),
                () -> assertThat(usersFound.get(1).getAge(), is(22)),
                () -> assertThat(usersFound.get(2).getName(), is("Pedro 3")),
                () -> assertThat(usersFound.get(2).getSex(), is(Sex.MASCULINO)),
                () -> assertThat(usersFound.get(2).getEmail(), is("pedro.sousa@dcx.ufpb.br")),
                () -> assertThat(usersFound.get(2).getPhone(), is("+5583986862912")),
                () -> assertThat(usersFound.get(2).getAge(), is(22))
        );

        verify(userRepository, times(1)).findAll();
    }
}