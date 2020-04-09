package ru.itis.semesterwork.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sandbox")
@ToString(exclude = {"user"})
public class Sandbox {
    @Id
    private String id;

    private String name;
    private String htmlCode;
    private String jsCode;
    private String cssCode;

    @ManyToOne(targetEntity = User.class)
    private User user;
}
