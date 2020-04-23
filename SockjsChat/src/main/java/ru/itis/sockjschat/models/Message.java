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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "room_id")
    private Long roomId;
}
