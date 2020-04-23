package ru.itis.sockjschat.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User sender;
    private Long roomId;}
