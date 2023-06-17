package com.company.secondproject.service.mapper;

import com.company.secondproject.dto.CardDto;
import com.company.secondproject.dto.ResponseDto;
import com.company.secondproject.modul.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.plaf.PanelUI;

@RequiredArgsConstructor
@Component
public class CardMapper {
    private final UserMapper userMapper;
    public CardDto toDto(Card card){
        return CardDto.builder()
                .cardId(card.getCardId())
                .cardNumber(card.getCardNumber())
                .cardName(card.getCardName())
                .amount(card.getAmount())
                .type(card.getType())
                .userId(card.getUserId())
                .user(this.userMapper.toDto(card.getUser()))
                .build();
    }
    public Card toEntity(CardDto dto){
        Card card = new Card();
        card.setCardName(dto.getCardName());
        card.setCardNumber(dto.getCardNumber());
        card.setUserId(dto.getUserId());
        card.setAmount(dto.getAmount());
        card.setType(dto.getType());
        return card;
    }
    public CardDto toDtoWithUser(Card card){
        return CardDto.builder()
                .cardId(card.getCardId())
                .cardName(card.getCardName())
                .cardNumber(card.getCardNumber())
                .amount(card.getAmount())
                .type(card.getType())
                .userId(card.getUserId())
                .user(this.userMapper.toDto(card.getUser()))
                .build();
    }

    public CardDto toDtoNotUser(Card card){
        return CardDto.builder()
                .cardId(card.getCardId())
                .cardName(card.getCardName())
                .cardNumber(card.getCardNumber())
                .amount(card.getAmount())
                .type(card.getType())
                .userId(card.getUserId())
                .build();
    }
    public void updateEntityFromDto(CardDto dto,Card card){
        if (dto== null){
            return;
        }
        if (dto.getCardName()!=null){
            card.setCardName(dto.getCardName());
        }
        if (dto.getCardNumber()!=null){
            card.setCardNumber(dto.getCardNumber());
        }
      if (dto.getType()!= null){
          card.setType(dto.getType());
      }
        if (dto.getAmount()!=null){
            card.setAmount(dto.getAmount());
        }
    }
}
