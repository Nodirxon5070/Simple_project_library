package com.company.secondproject.modul;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    /*@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_s", allocationSize = 1)
    @GeneratedValue(generator = "user_id_seq")*/
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer userId;// user_id
    @Column(name = "firstname")
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<Card> cards;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
