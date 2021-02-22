package com.ghostdovahkiin.librapi.user.v1;

import com.ghostdovahkiin.librapi.user.User;
import com.ghostdovahkiin.librapi.user.UserDTO;
import com.ghostdovahkiin.librapi.user.services.DeleteUserService;
import com.ghostdovahkiin.librapi.user.services.GetUserService;
import com.ghostdovahkiin.librapi.user.services.ListPageUserService;
import com.ghostdovahkiin.librapi.user.services.ListUserService;
import com.ghostdovahkiin.librapi.user.services.SaveUserService;
import com.ghostdovahkiin.librapi.user.services.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/api/users")
public class UserController {

    private final ListUserService listUserService;
    private final GetUserService getUserService;
    private final SaveUserService saveUserService;
    private final DeleteUserService deleteUserService;
    private final UpdateUserService updateUserService;
    private final ListPageUserService listPageUserService;

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAll() {
        return UserDTO.fromAll(listUserService.findAll());
    }

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> listPageUser(Pageable pageable) {
        return UserDTO.fromPage(listPageUserService.listPages(pageable));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findByID(@PathVariable Long id) {
        return UserDTO.from(getUserService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody UserDTO userDTO){
        saveUserService.save(User.to(userDTO));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        deleteUserService.delete(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id){
        updateUserService.update(userDTO, id);
    }
}
