package gigjob.service;

import gigjob.model.response.SessionResponse;
import gigjob.model.response.SessionShopResponse;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface SessionService {
    /**
     * Query the timekeeping date of a shop in 1 day
     *
     * @param shopId {@code UUID}
     * @param date   {@code Date}
     * @return List<SessionShopResponse>
     * @author Vien Binh
     */
    List<SessionShopResponse> getSessionByShopId(UUID shopId, Date date);

    /**
     * Check in working session. Create working session for worker
     *
     * @param workerId {@code UUID}
     * @param jobId    {@code Long}
     * @return SessionResponse
     * @author Vien Binh
     */
    SessionResponse checkIn(UUID workerId, Long jobId);
}
