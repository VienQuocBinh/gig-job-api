package gigjob.service;

import com.google.firebase.messaging.*;
import gigjob.firebase.messaging.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final FirebaseMessaging firebaseMessaging;

    public BatchResponse sendNotification(Notice notice) {
        List<String> registrationTokens = notice.getRegistrationTokens();
        Notification notification = Notification.builder()
                .setTitle(notice.getSubject())
                .setBody(notice.getContent())
                .build();

        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(registrationTokens)
                .setNotification(notification)
                .putAllData(notice.getData())
                .build();

        BatchResponse batchResponse = null;
        try {
            batchResponse = firebaseMessaging.sendMulticast(message);
        } catch (FirebaseMessagingException e) {
            log.info("Firebase error {}", e.getMessage());
        }
        if (batchResponse.getFailureCount() > 0) {
            List<SendResponse> responses = batchResponse.getResponses();
            List<String> failedTokens = new ArrayList<>();
            for (int i = 0; i < responses.size(); i++) {
                if (!responses.get(i).isSuccessful()) {
                    failedTokens.add(registrationTokens.get(i));
                }
            }
            log.info("List of tokens that caused failures: " + failedTokens);
        }
        return batchResponse;
    }
}
