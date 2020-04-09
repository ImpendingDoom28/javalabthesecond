package ru.itis.semesterwork.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "verification_token")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @Column(name="expiry_date")
    private LocalDateTime expiryDate;
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToOne
    private User user;
}
