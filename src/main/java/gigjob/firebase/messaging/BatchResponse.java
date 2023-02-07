package gigjob.firebase.messaging;

import com.google.firebase.internal.NonNull;
import com.google.firebase.messaging.SendResponse;

import java.util.List;

public interface BatchResponse {
    @NonNull
    List<SendResponse> getResponses();

    int getSuccessCount();

    int getFailureCount();
}
