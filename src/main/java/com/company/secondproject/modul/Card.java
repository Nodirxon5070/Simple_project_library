package com.company.secondproject.modul;

import ch.qos.logback.core.model.INamedModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "card_id")
    @SequenceGenerator(name = "card_id_sequence",sequenceName = "card_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "card_id_sequence")
    private Integer cardId;
    @Column(name = "card_number")
    private Long cardNumber;
    @Column(name = "card_name")
    private String cardName;
    private String type;
    private Double amount;



    @Column(name = "user_id")
    private Integer userId;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
