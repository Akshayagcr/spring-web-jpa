package com.learning.spring_web_jpa.rest.controller;

import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.learning.spring_web_jpa.domain.dto.UserRequest;
import com.learning.spring_web_jpa.domain.dto.UserResponse;
import com.learning.spring_web_jpa.domain.dto.UsersResponse;
import com.learning.spring_web_jpa.domain.enums.SortDirection;
import com.learning.spring_web_jpa.domain.enums.SortProperty;
import com.learning.spring_web_jpa.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest){
        return userService.save(userRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UsersResponse getAllUser(@RequestParam(value = "sort", defaultValue = "FIRST_NAME") SortProperty sortProperty,
                                    @RequestParam(value = "direction", defaultValue = "ASC") SortDirection sortDirection,
                                    @RequestParam(value = "per_page", defaultValue = "10") int perPage,
                                    @RequestParam(value = "page", defaultValue = "1") int page) {
        return userService.getAllUsers(sortProperty, sortDirection, perPage, page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse put(@PathVariable(value = "id") Long id, @Valid @RequestBody UserRequest userRequest) {
        return userService.put(id, userRequest);
    }

    @PatchMapping(path = "/{id}", consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse patch(@PathVariable(value = "id") Long id,
                              @RequestBody JsonMergePatch jsonMergePatch) {
        return userService.patch(id, jsonMergePatch);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
    }

}
