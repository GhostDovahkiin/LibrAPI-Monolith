package com.ghostdovahkiin.librapi.user;

import com.ghostdovahkiin.librapi.user.services.ListPageUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static com.ghostdovahkiin.librapi.user.builders.UserBuilder.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListPageUserServiceTest {

    @Mock
    private UserRepository userRepository;
    private ListPageUserServiceImpl listPageUserService;

    @BeforeEach
    void setUp() {
        this.listPageUserService = new ListPageUserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("Should return all users with pagination")
    void shouldReturnUsersAsAPage() {
        when(listPageUserService.listPages(PageRequest.of(0, 2)))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, createUser().build())));

        Page<User> usersPageable = listPageUserService.listPages(PageRequest.of(0,2));

        assertAll("Users",
                () -> assertThat(usersPageable.getNumber(), is(0)),
                () -> assertThat(usersPageable.getSize(), is(2)),
                () -> assertThat(usersPageable.getContent().get(0).getSex(), is(Sex.MALE)),
                () -> assertThat(usersPageable.getContent().get(0).getEmail(), is("pedro.sousa@dcx.ufpb.br")),
                () -> assertThat(usersPageable.getContent().get(0).getPhone(), is("+5583986862912")),
                () -> assertThat(usersPageable.getContent().get(0).getAge(), is(22)),
                () -> assertThat(usersPageable.getContent().get(0).getName(), is("Pedro"))
        );


    }
}