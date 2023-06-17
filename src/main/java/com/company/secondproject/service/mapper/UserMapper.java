package com.company.secondproject.service.mapper;

import com.company.secondproject.dto.UserDto;
import com.company.secondproject.modul.User;
import com.company.secondproject.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Lazy
    @Autowired
    private CardMapper cardMapper;

    @Lazy
    @Autowired
    private CardService cardService;

    public UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }

    public UserDto toDtoWithCard(User user){
        return UserDto.builder()
                .userId(user.getUserId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .cards(this.cardService.getCardFromUserId(user.userId))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public void updateUserFromDto(UserDto dto, User user) {
        if (dto == null) {
            return;
        }
        if (dto.getFirstname() != null) {
            user.setFirstname(dto.getFirstname());
        }
        if (dto.getLastname() != null) {
            user.setLastname(dto.getLastname());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
    }


}
