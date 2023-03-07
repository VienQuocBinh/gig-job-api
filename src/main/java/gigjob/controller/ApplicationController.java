package gigjob.controller;

import gigjob.model.request.ApplicationApplyRequest;
import gigjob.model.response.ApplicationResponse;
import gigjob.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/v1/application/worker/{id}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationByWorkerId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                applicationService.getApplicationsByWorkerId(id)
        );
    }

    @PostMapping("/v1/application")
    public ResponseEntity<Object> apply(@RequestBody ApplicationApplyRequest applyRequest) {
        try {
            applicationService.apply(applyRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Application applied successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to apply");
        }
    }
}
