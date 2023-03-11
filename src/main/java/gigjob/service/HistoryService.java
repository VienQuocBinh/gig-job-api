package gigjob.service;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import gigjob.entity.History;

import gigjob.repository.HistoryRepository;

public class HistoryService {
    @Autowired
    private HistoryRepository repo;

    public List<History> ListAll(){
        return repo.findAll();
    }

    public void save (History wallet){
        repo.save(wallet);
    }

    public History get(Long id){
        return repo.findById(id).get();
    }
    public void Delete(Long id){
        repo.deleteById(id);
    }
}
