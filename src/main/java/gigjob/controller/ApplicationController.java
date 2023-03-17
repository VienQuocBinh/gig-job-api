package gigjob.controller;

import gigjob.model.request.ApplicationApplyRequest;
import gigjob.model.response.ApplicationResponse;
import gigjob.model.response.ErrorResponse;
import gigjob.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/v1/application/worker/{id}")
    @Operation(description = "Get all the jobs which are applied by a workerId")
    public ResponseEntity<List<ApplicationResponse>> getApplicationByWorkerId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                applicationService.getApplicationsByWorkerId(id)
        );
    }

    @PostMapping("/v1/application")
    @Operation(description = "Apply the job if already applied then throws error. status: PENDING: 0, ACCEPTED: 1, REJECTED: 2")
    public ResponseEntity<Object> apply(@RequestBody ApplicationApplyRequest applyRequest) {
        try {
            applicationService.apply(applyRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Application applied successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to apply");
        }
    }

    @GetMapping("/v1/application/shop/{id}")
    public ResponseEntity<Object> getApplicationsByShopID(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                applicationService.getApplicationsByShopId(UUID.fromString(id))
        );
    }

    @GetMapping("/v1/application/job/{id}/accepted")
    public ResponseEntity<Object> getAcceptedApplicationById(@PathVariable("id") Long jobId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                applicationService.findAcceptedApplications(jobId)
        );
    }

    @GetMapping("/v1/application/job/{id}/rejected")
    public ResponseEntity<Object> getRejectedApplicationById(@PathVariable("id") Long jobId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                applicationService.findRejectedApplications(jobId)
        );
    }

    @GetMapping("/v1/application/job/accepted/{shopId}")
    @CrossOrigin
    public ResponseEntity<Object> getAcceptedApplicationsByShopId(@PathVariable String shopId){
        return ResponseEntity.status(HttpStatus.OK).body(
                applicationService.findAcceptedApplications(UUID.fromString(shopId))
        );
    }

    @PatchMapping("/v1/application/accept")
    public ResponseEntity<Object> acceptApplicationById(@RequestBody ApplicationApplyRequest applyRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    applicationService.acceptApplication(applyRequest)
            );
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ErrorResponse.builder()
                            .timestamp(new Date())
                            .message(e.getMessage())
                            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .build()
            );
        }
    }

    @PatchMapping("/v1/application/reject")
    public ResponseEntity<Object> rejectApplicationById(@RequestBody ApplicationApplyRequest applyRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    applicationService.rejectApplication(applyRequest)
            );
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ErrorResponse.builder()
                            .timestamp(new Date())
                            .message(e.getMessage())
                            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .build()
            );
        }
    }
}
