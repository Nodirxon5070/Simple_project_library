package com.company.secondproject.controller;

import com.company.secondproject.dto.CardDto;
import com.company.secondproject.dto.ResponseDto;
import com.company.secondproject.dto.SimpleCRUD;
import com.company.secondproject.dto.UserDto;
import com.company.secondproject.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "card")
public class CardController implements SimpleCRUD<Integer, CardDto> {

    @Autowired
    private  CardService cardService;

    @Override
    @PostMapping(value = "/create")
    public ResponseDto<CardDto> create(@RequestBody CardDto dto) {

        return this.cardService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    public ResponseDto<CardDto> get(@RequestParam(value = "id") Integer entityId) {
        return this.cardService.get(entityId);
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseDto<CardDto> update(@RequestParam(value = "id") Integer entityId,
                                       @RequestBody CardDto dto) {
        return this.cardService.update(entityId,dto);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseDto<CardDto> delete(@RequestParam Integer entityId) {
        return this.cardService.delete(entityId);
    }
}
