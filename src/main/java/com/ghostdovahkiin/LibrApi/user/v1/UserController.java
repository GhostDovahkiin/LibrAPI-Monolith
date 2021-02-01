package com.ghostdovahkiin.LibrApi.user.v1;

import com.ghostdovahkiin.LibrApi.user.User;
import com.ghostdovahkiin.LibrApi.user.UserDTO;
import com.ghostdovahkiin.LibrApi.user.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/users")
public class UserController {

    private final ListAllUserService listAllUserService;
    private final GetOneUserService getOneUserService;
    private final SaveUserService saveUserService;
    private final DeleteUserService deleteUserService;
    private final UpdateUserService updateUserService;
    private final ListPageUserService listPageUserService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAll() {
        return UserDTO.fromAll(listAllUserService.findAll());
    }

    @GetMapping(params = {"pages", "size"})
    public Page<UserDTO> listPageUser(@RequestParam("pages") Integer pages, @RequestParam("size") Integer size) {
        return UserDTO.fromPage(listPageUserService.listPages(pages, size));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findByID(@PathVariable Long id) {
        return UserDTO.from(getOneUserService.findById(id));
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
        updateUserService.update(User.to(userDTO), id);
    }
}
