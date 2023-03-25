package gigjob.controller;

import gigjob.model.request.HistoryRequest;
import gigjob.model.request.HistoryUpdateRequest;
import gigjob.model.response.HistoryResponse;
import gigjob.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService service;

//    @GetMapping("/v1/history/{id}")
//    public ResponseEntity<History> get(@PathVariable Long id) {
//        try {
//            History history = service.get(id);
//            return new ResponseEntity<>(history, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/v1/history/worker/{id}")
    public ResponseEntity<List<HistoryResponse>> getByWorkerId(@PathVariable("id") UUID id) {
        List<HistoryResponse> historyResponses = service.getByWorkerId(id);
        return ResponseEntity.status(HttpStatus.OK).body(historyResponses);
    }

    @PostMapping("/v1/history")
    public ResponseEntity<HistoryResponse> create(@RequestBody HistoryRequest historyRequest) {
        HistoryResponse historyResponse = service.create(historyRequest);
        return ResponseEntity.status(HttpStatus.OK).body(historyResponse);
    }

    @PutMapping("/v1/history")
    public ResponseEntity<HistoryResponse> update(@RequestBody HistoryUpdateRequest historyRequest) {
        HistoryResponse historyResponse = service.update(historyRequest);
        return ResponseEntity.status(HttpStatus.OK).body(historyResponse);
    }

    @DeleteMapping("/v1/history/{id}")
    public void Delete(@PathVariable Long id) {
        service.Delete(id);
    }

}
