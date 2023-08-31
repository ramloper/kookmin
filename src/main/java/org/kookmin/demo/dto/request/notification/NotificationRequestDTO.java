package org.kookmin.demo.dto.request.notification;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.kookmin.demo.domain.Notification;
import org.w3c.dom.Text;


@Data
@Getter
public class NotificationRequestDTO {

    private String title;

    private String content;

    private Boolean aBoolean;

    public Notification toEntity(){
        return Notification.builder()
                .title(title)
                .content(content)
                .aBoolean(true)
                .build();
    }
}
