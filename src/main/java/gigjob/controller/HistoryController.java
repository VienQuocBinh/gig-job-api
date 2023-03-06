package gigjob.controller;

import java.util.List;
import java.util.NoSuchElementException;

import gigjob.entity.History;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gigjob.service.HistoryService;
import gigjob.service.WorkerService;

@RestController

public class HistoryController {
    @Autowired 
    private HistoryService service;

    @GetMapping("/Historys")
    public List<History> List(){
        return service.ListAll();
    }

    @GetMapping("/Historys/{id}")
    public ResponseEntity<History> get(@PathVariable Long id) {
        try{
            History history = service.get(id);
        return new ResponseEntity<History>(history ,HttpStatus.OK);
        }catch (NoSuchElementException e) {
              return new ResponseEntity<History>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/Historys")
    public void add(@RequestBody History history) {
        service.save(history);
    }
    @PutMapping("/Historys/{id}")
    public ResponseEntity<?> update(@RequestBody History history,
            @PathVariable Long id) {
            try{
                History existWorker = service.get(id);
                service.save(history);
                
                return new ResponseEntity<>(HttpStatus.OK);
            }catch (NoSuchElementException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    @DeleteMapping("/historys/{id}")
    public void Delete(@PathVariable Integer id) {
        service.Delete(id);
    }    
        
      
}
