package gigjob.controller;

import com.google.firebase.messaging.BatchResponse;
import gigjob.firebase.messaging.Notification;
import gigjob.firebase.messaging.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/v1/notification/send")
    public BatchResponse sendNotification(@RequestBody Notification notification) {
        return notificationService.sendNotification(notification);
    }
}
