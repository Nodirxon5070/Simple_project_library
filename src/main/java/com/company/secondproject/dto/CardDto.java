package com.company.secondproject.dto;

import com.company.secondproject.modul.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    private Integer cardId;
    private Long cardNumber;
    private String cardName;
    private String type;
    private Double amount;
    private Integer userId;
    private UserDto user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
