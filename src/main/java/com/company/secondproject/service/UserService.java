package com.company.secondproject.service;

import com.company.secondproject.dto.ResponseDto;
import com.company.secondproject.dto.SimpleCRUD;
import com.company.secondproject.dto.UserDto;
import com.company.secondproject.modul.User;
import com.company.secondproject.repository.UserRepository;
import com.company.secondproject.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
        implements SimpleCRUD<Integer, UserDto> {

    @Autowired
    private UserRepository userRepository;
    private List<User> userList;
    private Integer UserIx;

    @Autowired
    private UserMapper userMapper;

    public UserService() {
        this.userList = new ArrayList<>();
        this.UserIx = 0;

    }

    @Override
    public ResponseDto<UserDto> create(UserDto dto) {
        if (this.userRepository.existsByEmail(dto.getEmail())) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("This email: %s already exist!", dto.getEmail()))
                    .build();
        }

        if (checkEmailPattern(dto.getEmail())) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("Given %s The email was not valid", dto.getEmail()))
                    .build();
        }

        try {
            User user = this.userMapper.toEntity(dto);
            user.setCreatedAt(LocalDateTime.now());
            this.userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.userMapper.toDto(user))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message(String.format("User while saving error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<UserDto> get(Integer entityId) {
        Optional<User> optional = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found!")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("OK")
                .data(this.userMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<UserDto> update(Integer entityId, UserDto dto) {
        try {
            Optional<User> optional = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<UserDto>builder()
                        .message("User is not found!")
                        .code(-1)
                        .build();
            }
            this.userMapper.updateUserFromDto(dto,optional.get());
            optional.get().setUpdatedAt(LocalDateTime.now());
            this.userRepository.save(optional.get());
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.userMapper.toDto(optional.get()))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message(String.format("User while saving error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<UserDto> delete(Integer entityId) {
        try {
            Optional<User> optional = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found!")
                    .code(-1)
                    .build();
        }
            if (optional.get().getUserId().equals(entityId)) {
                optional.get().setDeletedAt(LocalDateTime.now());
                this.userRepository.save(optional.get());
                return ResponseDto.<UserDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.userMapper.toDto(optional.get()))
                        .build();
            }

        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message(String.format("User while saving error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
        return ResponseDto.<UserDto>builder()
                .message("User is not found!")
                .code(-1)
                .build();

    }

    /*private boolean checkEmailPattern(String email){
        String[] array = email.split("@");
        if (array.length == 2){
            return array[1].equals("gmail.com");
        }
        else{
            return true;
        }
    }*/

    private boolean checkEmailPattern(String email) {
        String[] array = email.split("@");
        if (array.length == 2) {
            return !array[1].equals("gmail.com");
        } else {
            return true;
        }
    }
}
