package ru.itis.semesterwork.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import ru.itis.semesterwork.models.User;

@Getter
@Setter
public class OnRegisterSuccessEvent extends ApplicationEvent {

    private User user;
    private String contextPath;

    public OnRegisterSuccessEvent(User user, String contextPath) {
        super(user);
        this.user = user;
        this.contextPath = contextPath;
    }

}
