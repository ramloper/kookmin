package org.kookmin.demo.service;

import org.kookmin.demo.domain.Notification;
import org.kookmin.demo.dto.request.notification.NotificationRequestDTO;
import org.kookmin.demo.dto.request.notification.NotificationToMainModifyRequestDTO;

import java.util.List;

public interface NotificationService {
    public List<Notification> notificationFindAllByToMain();

    public List<Notification> notificationFindAll();

    public void notificationSave(NotificationRequestDTO dto);

    void notificationToMainPost(Long id);

    void notificationToMainDelete(Long id);
}
