package org.kookmin.demo.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.kookmin.demo.dto.request.notification.NotificationToMainModifyRequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Notification extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean aBoolean;

    public void toMainPost(){
        this.setABoolean(true);
    }

    public void toMainDelete(){
        this.setABoolean(false);
    }
}
