package ru.itis.semesterwork.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Указываем, что этот класс представляет собой сущность
@Entity(name = "user_codep")
@ToString(exclude = {"verificationToken", "profile"})
public class User {
    //Указываем, что это поле - primary key
    @Id
    //Говорим бд, чтобы оно само генерировало нам primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Задаём имя колонке в таблице
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "hash_password")
    private String hashPassword;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;
    //Указываем тип отношения с другой сущностью
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private VerificationToken verificationToken;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Sandbox> sandboxList;
}
