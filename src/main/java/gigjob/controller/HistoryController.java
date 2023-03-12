package gigjob.controller;

import gigjob.entity.History;
import gigjob.service.impl.HistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryServiceImpl service;

    @GetMapping("/v1/history")
    public List<History> List() {
        return service.ListAll();
    }

    @GetMapping("/v1/history/{id}")
    public ResponseEntity<History> get(@PathVariable Long id) {
        try {
            History history = service.get(id);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/history")
    public void add(@RequestBody History history) {
        service.save(history);
    }

    @PutMapping("/history/{id}")
    public ResponseEntity<?> update(@RequestBody History history,
                                    @PathVariable Long id) {
        try {
            History existWorker = service.get(id);
            service.save(history);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/history/{id}")
    public void Delete(@PathVariable Long id) {
        service.Delete(id);
    }

}
