package ru.itis.semesterwork.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sandbox")
@ToString(exclude = {"user"})
@Builder
public class Sandbox {
    @Id
    private String id;

    private String name;
    private String htmlCode;
    private String jsCode;
    private String cssCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
