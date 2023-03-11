package gigjob.controller;

import gigjob.entity.Worker;
import gigjob.model.response.WorkerResponse;
import gigjob.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkerController {
    private final WorkerService service;

    @GetMapping("/workers")
    public List<Worker> List() {
        return service.ListAll();
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<Worker> get(@PathVariable Integer id) {
        try {
            Worker worker = service.get(id);
            return new ResponseEntity<>(worker, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/workers/account/{id}")
    public ResponseEntity<WorkerResponse> getByAccountId(@PathVariable String id) {
        try {
            WorkerResponse worker = service.getByAccountId(id);
            return new ResponseEntity<>(worker, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/workers")
    public void add(@RequestBody Worker worker) {
        service.save(worker);
    }

    @PutMapping("/workers/{id}")
    public ResponseEntity<?> update(@RequestBody Worker worker,
                                    @PathVariable Integer id) {
        try {
            Worker existWorker = service.get(id);
            service.save(worker);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/workers/{id}")
    public void Delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
