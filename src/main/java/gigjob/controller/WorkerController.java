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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping("/workers")
    public List<Worker> List() {
        return workerService.ListAll();
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<Worker> get(@PathVariable String id) {
        try {
            Worker worker = workerService.get(UUID.fromString(id));
            return new ResponseEntity<>(worker, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/workers/account/{id}")
    public ResponseEntity<WorkerResponse> getByAccountId(@PathVariable String id) {
        try {
            WorkerResponse worker = workerService.getByAccountId(id);
            return new ResponseEntity<>(worker, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/workers")
    public void add(@RequestBody Worker worker) {
        workerService.save(worker);
    }

    @PutMapping("/workers/{id}")
    public ResponseEntity<?> update(@RequestBody Worker worker,
                                    @PathVariable String id) {
        try {
            Worker existWorker = workerService.get(UUID.fromString(id));
            workerService.save(worker);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/workers/{id}")
    public void Delete(@PathVariable String id) {
        workerService.delete(UUID.fromString(id));
    }
}