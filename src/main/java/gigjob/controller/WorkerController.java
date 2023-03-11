package gigjob.controller;

import gigjob.entity.Worker;
import gigjob.model.response.WorkerResponse;
import gigjob.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/v1/worker/{id}")
    public ResponseEntity<WorkerResponse> getWorkerById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                workerService.getWorkerById(id)
        );
    }
}
