package org.kookmin.demo.dto.request.notification;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NotificationToMainModifyRequestDTO {
    private Long id;

    private boolean aBoolean;
}
