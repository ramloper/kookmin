package org.kookmin.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.domain.Notification;
import org.kookmin.demo.dto.request.notification.NotificationRequestDTO;
import org.kookmin.demo.dto.request.notification.NotificationToMainModifyRequestDTO;
import org.kookmin.demo.repository.NotificationRepository;
import org.kookmin.demo.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    public List<Notification> notificationFindAllByToMain(){
        List<Notification> notificationList = notificationRepository.findAllByToMain(true);

        return notificationList;
    }

    public List<Notification> notificationFindAll(){
        List<Notification> notificationList = notificationRepository.findAll();

        return notificationList;
    }

    public void notificationSave(NotificationRequestDTO dto){
        notificationRepository.save(dto.toEntity());
    }

    @Transactional
    public void notificationToMainPost(Long id){
        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.toMainPost();
    }
    @Transactional
    public void notificationToMainDelete(Long id){
        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.toMainDelete();
    }

}
