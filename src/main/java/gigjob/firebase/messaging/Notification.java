package gigjob.firebase.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {
    // Subject notification on firebase
    private String subject;
    // Content notification on firebase
    private String content;
    // Image Url
    private String imageUrl;
    // Map các data
    private Map<String, String> data;
    // FCM registration token
    private List<String> registrationTokens;
}
