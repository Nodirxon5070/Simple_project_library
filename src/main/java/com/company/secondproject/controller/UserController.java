package com.company.secondproject.controller;

import com.company.secondproject.dto.ResponseDto;
import com.company.secondproject.dto.SimpleCRUD;
import com.company.secondproject.dto.UserDto;
import com.company.secondproject.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public record UserController(UserService userService) implements SimpleCRUD<Integer, UserDto> {

    @Override
    @PostMapping(value = "/create")
    public ResponseDto<UserDto> create(@RequestBody UserDto dto) {
        return this.userService.create(dto);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseDto<UserDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.userService.get(entityId);
    }

    @Override
    @PutMapping(value = "/update/{id}")
    public ResponseDto<UserDto> update(@PathVariable(value = "id") Integer entityId,
                                       @RequestBody UserDto dto) {
        return this.userService.update(entityId, dto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<UserDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.userService.delete(entityId);
    }
}
