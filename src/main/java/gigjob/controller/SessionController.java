package gigjob.controller;

import gigjob.model.request.CheckInRequest;
import gigjob.model.response.SessionResponse;
import gigjob.model.response.SessionShopResponse;
import gigjob.service.SessionService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    /**
     * Query the timekeeping date of a shop in 1 day
     *
     * @param shopId {@code UUID}
     * @param date   {@code Date}
     * @return List<SessionShopResponse>
     * @author Vien Binh
     */
    @GetMapping("/v1/session/shop/{shopId}")
    public ResponseEntity<List<SessionShopResponse>> getSessionListByShopAndDate(
            @PathVariable("shopId") UUID shopId,
            @RequestParam
            @Parameter(description = "format: yyyy-MM-dd")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.getSessionByShopId(shopId, date));
    }

    @PostMapping("/v1/session/check-in")
    public ResponseEntity<SessionResponse> checkIn(@RequestBody CheckInRequest checkInRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sessionService.checkIn(checkInRequest.getWorkerId(), checkInRequest.getJobId()));
    }
}
