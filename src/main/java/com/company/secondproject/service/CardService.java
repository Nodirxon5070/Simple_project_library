package com.company.secondproject.service;

import com.company.secondproject.dto.CardDto;
import com.company.secondproject.dto.ResponseDto;
import com.company.secondproject.dto.SimpleCRUD;
import com.company.secondproject.dto.UserDto;
import com.company.secondproject.modul.Card;
import com.company.secondproject.modul.User;
import com.company.secondproject.repository.CardRepository;
import com.company.secondproject.service.mapper.CardMapper;
import com.company.secondproject.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService implements SimpleCRUD<Integer, CardDto> {

    private final CardRepository cardRepository;
    private final UserService userService;
    private final CardMapper cardMapper;
    private final UserMapper userMapper;

    public ResponseDto<CardDto> create(CardDto dto) {

        if (this.userService.get(dto.getUserId()).getData() == null) {
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("User is not found!")
                    .build();
        }
        try {

            Card card = this.cardMapper.toEntity(dto);
            card.setCreatedAt(LocalDateTime.now());
            this.cardRepository.save(card);
            return ResponseDto.<CardDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.cardMapper.toDtoWithUser(card))
                    .build();


        } catch (Exception e) {
            return ResponseDto.<CardDto>builder()
                    .message(String.format("Card while saving not valid %s", e.getMessage()))
                    .code(-2)
                    .build();
        }


    }

    @Override
    public ResponseDto<CardDto> get(Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("Card is not found")
                    .build();
        }
        return ResponseDto.<CardDto>builder()
                .success(true)
                .message("OK")
                .data(this.cardMapper.toDtoNotUser(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<CardDto> update(Integer entityId, CardDto dto) {

        try {
            Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
            this.cardMapper.updateEntityFromDto(dto, optional.get());
            optional.get().setUpdatedAt(LocalDateTime.now());
            this.cardRepository.save(optional.get());
            return ResponseDto.<CardDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.cardMapper.toDto(optional.get()))
                    .build();


        } catch (Exception e) {
            return ResponseDto.<CardDto>builder()
                    .message(String.format("Card while saving not valid %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    @Override
    public ResponseDto<CardDto> delete(Integer entityId) {
        try {
            Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
            optional.get().setDeletedAt(LocalDateTime.now());
            this.cardRepository.save(optional.get());
            return ResponseDto.<CardDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.cardMapper.toDto(optional.get()))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CardDto>builder()
                    .message(String.format("Card while saving not valid %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    public Set<CardDto> getCardFromUserId(Integer userId) {
        User user = userMapper.toEntity(this.userService.get(userId).getData());
        Set<CardDto> set = user.getCards().stream().map(this.cardMapper::toDtoNotUser).collect(Collectors.toSet());

        return set;
    }
}
