package com.company.secondproject.repository;

import com.company.secondproject.modul.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer> {

    Optional<Card> findByCardIdAndDeletedAtIsNull(Integer cardId);
}
