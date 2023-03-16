package gigjob.controller;

import gigjob.entity.Worker;
import gigjob.model.request.WorkerRegisterRequest;
import gigjob.model.request.WorkerUpdateRequest;
import gigjob.model.response.AccountResponse;
import gigjob.model.response.WorkerDetailResponse;
import gigjob.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/v1/worker/{id}")
    public ResponseEntity<WorkerDetailResponse> getWorkerById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                workerService.getWorkerById(id)
        );
    }

    @GetMapping("/v1/workers")
    public List<Worker> getWorkerList() {
        return workerService.ListAll();
    }

    @GetMapping("/v1/worker/account/{id}")
    public ResponseEntity<WorkerDetailResponse> getByAccountId(@PathVariable String id) {
        try {
            WorkerDetailResponse worker = workerService.getByAccountId(id);
            return new ResponseEntity<>(worker, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/v1/worker")
    public ResponseEntity<AccountResponse> create(@RequestBody WorkerRegisterRequest workerRegisterRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(workerService.create(workerRegisterRequest));
    }

    @PutMapping("/v1/worker")
    public ResponseEntity<WorkerDetailResponse> update(@RequestBody WorkerUpdateRequest workerUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(workerService.update(workerUpdateRequest));
    }

    @DeleteMapping("/workers/{id}")
    public void Delete(@PathVariable String id) {
        workerService.delete(UUID.fromString(id));
    }
}