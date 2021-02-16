package com.ghostdovahkiin.librapi.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostdovahkiin.librapi.user.services.DeleteUserService;
import com.ghostdovahkiin.librapi.user.services.GetUserService;
import com.ghostdovahkiin.librapi.user.services.ListUserService;
import com.ghostdovahkiin.librapi.user.services.ListPageUserService;
import com.ghostdovahkiin.librapi.user.services.SaveUserService;
import com.ghostdovahkiin.librapi.user.services.UpdateUserService;
import com.ghostdovahkiin.librapi.user.v1.UserController;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static com.ghostdovahkiin.librapi.user.builders.UserBuilder.createUser;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@DisplayName("Tests all implemented tests from services if working on controller")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ListUserService listUserService;
    @MockBean
    private GetUserService getUserService;
    @MockBean
    private SaveUserService saveUserService;
    @MockBean
    private UpdateUserService updateUserService;
    @MockBean
    private DeleteUserService deleteUserService;
    @MockBean
    private ListPageUserService listPageUserService;

    private final String URLREQ = "/v1/api/users";

    @Test
    @DisplayName("Should find and return all users")
    void shouldFindAllUsers() throws Exception{
        when(listUserService.findAll()).thenReturn(
                Lists.newArrayList(
                        createUser().id(1234L).name("teste1").build(),
                        createUser().id(2468L).name("teste2").build(),
                        createUser().id(1357L).name("teste3").build()
        ));

        mockMvc.perform(get(URLREQ + "/all").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1234)))
                .andExpect(jsonPath("$[0].name", is("teste1")))
                .andExpect(jsonPath("$[0].sex", is("MASCULINO")))
                .andExpect(jsonPath("$[1].id", is(2468)))
                .andExpect(jsonPath("$[1].name", is("teste2")))
                .andExpect(jsonPath("$[1].sex", is("MASCULINO")))
                .andExpect(jsonPath("$[2].id", is(1357)))
                .andExpect(jsonPath("$[2].name", is("teste3")))
                .andExpect(jsonPath("$[2].sex", is("MASCULINO"))
        );
        verify(listUserService).findAll();

    }

    @Test
    @DisplayName("Should find and return one user")
    void shouldFindUserById() throws Exception{
        when(getUserService.findById(1234L))
                .thenReturn(createUser()
                                .id(1234L)
                                .name("teste1")
                                .age(22)
                                .email("teste@email.com")
                                .build());

        mockMvc.perform(get(URLREQ + "/{id}", 1234L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1234)))
                .andExpect(jsonPath("$.name", is("teste1")))
                .andExpect(jsonPath("$.sex", is("MASCULINO")))
                .andExpect(jsonPath("$.age", is(22)))
                .andExpect(jsonPath("$.email", is("teste@email.com"))
        );
        verify(getUserService, times(1)).findById(anyLong());

    }

    @Test
    @DisplayName("Should save an User")
    void shouldSaveUser() throws Exception {
        mockMvc.perform(post(URLREQ).contentType(MediaType.APPLICATION_JSON)
                .content(readJson("UserDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated()
        );

        //fazer assert()

        verify(saveUserService, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should update an User")
    void shouldUpdateUser() throws Exception {
        mockMvc.perform(put(URLREQ + "/{id}", 145L)
                .content(readJson("UserDTOUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        //fazer assert()

        verify(updateUserService).update(any(UserDTO.class), eq(145L));
    }

    @Test
    @DisplayName("Should delete an User")
    void shouldRemoveUser() throws Exception {
        mockMvc.perform(delete(URLREQ + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteUserService).delete(anyLong());
    }

    @Test
    @DisplayName("Should return users with pagination")
    void shouldListUsersWithPagination() throws Exception {
        Page<User> userPagination = new PageImpl<>(Collections.singletonList(createUser().id(145485L).build()));
        when(listPageUserService.listPages(PageRequest.of(0,2))).thenReturn(userPagination);

        mockMvc.perform(get(URLREQ + "/?page=0&size=2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(145485)))
                .andExpect(jsonPath("$.content[0].name", is("Pedro")))
                .andExpect(jsonPath("$.content[0].age", is(22)))
                .andExpect(jsonPath("$.content[0].phone", is("+5583986862912"))
        );
    }


    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}